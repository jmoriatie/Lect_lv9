package controller;

import java.util.ArrayList;

import models.Cart;
import models.Item;
import models.User;

public class ItemManager {
	ArrayList<String> category; 
	ArrayList<Item> itemList;
	ArrayList<Cart> cart;
	ArrayList<Cart> payAll;

	public static ItemManager instance = new ItemManager();
	
	
	// 카테고리 추가
	private ItemManager() {
		category = new ArrayList<String>();
		itemList = new ArrayList<Item>(); // item 정보(이름, 가격, 카테고리)
		cart = new ArrayList<Cart>(); // userId, item 
		init(); // 실행용
	}
	
	void init() {
		category.add("과자");
		category.add("생선");
		category.add("육류");
		category.add("음료수");
		Item temp = new Item("새우깡", 1000, category.get(0));
		itemList.add(temp);
		temp = new Item("고등어", 2000, category.get(1));
		itemList.add(temp);
		temp = new Item("칸쵸", 3600, category.get(0));
		itemList.add(temp);
		temp = new Item("소고기", 6500, category.get(2));
		itemList.add(temp);
		temp = new Item("콜라", 500, category.get(3));
		itemList.add(temp);
		temp = new Item("새우", 1800, category.get(1));
		itemList.add(temp);
	}
		
	// 구입 => 물건 사는거
	public void shopping(String logId) {
		while(true) {
			// category print
			System.out.println("=== 카테고리 리스트 ===");
			printCategory();
			System.out.println("[취소: -1]");
			
			// select
			System.out.print("카테고리 선택 : ");
			String input = Shop.sc.next();
			int catIdx = select(input);
			
			if(catIdx == -1) { // 취소
				break;
			}
			else if(catIdx != -2) { 
				if(catIdx >= 0 && catIdx < category.size()) {
					// item print
					System.out.println("=== 상품 리스트 ===");
					printItemList(category.get(catIdx));
					System.out.println("[뒤로가기: -1]");
					
					// select
					System.out.print("아이템 선택 : ");
					input = Shop.sc.next();
					int selIdx = select(input);
					System.out.println("[취소: -1]");
					
					if(selIdx == -1) { 
						break;
					}
					else if(selIdx != -2) { 
						Item tempItem = selectItem(category.get(catIdx), selIdx);
						if(tempItem != null) {
							cart.add( new Cart(logId, tempItem) );
							System.out.println("[장바구니에 담았습니다]");
						} else System.out.println("[아이템 번호를 확인하세요]");
					} else System.out.println("[옳은 메뉴를 선택하세요]");	
				} else System.out.println("[없는 카테고리 입니다]");
			} else System.out.println("[옳은 메뉴를 선택하세요]");

		}
	}
	
	// 선택 메서드
	private int select(String input) {
		int select = -2;
		try {
			select = Integer.parseInt(input);
		} catch(Exception e) {
			System.out.println("@@@ ERR! 입력오류! @@@");
			e.printStackTrace();
		}
		return select;
	}
	
	// 카테고리 출력
	private void printCategory() {
		for(int i=0; i<category.size(); i++) {
			System.out.println(i+"] "+category.get(i));
		}
	}
	// 아이템 출력
	private void printItemList(String category) {
		int num = 0;
		for(int i=0; i<itemList.size(); i++) {
			Item item = itemList.get(i);
			if(item.getCategory().equals(category)) {
				System.out.printf("%d] %s(%d원)\n",num++, item.getItemName(), item.getPrice());
			}
		}
	}
	
	// 카테고리, 아이템 으로 만들어주기
	private Item selectItem(String category, int selectNum) {
		Item item = null;
		int num = 0;
		for(int i=0; i<itemList.size(); i++) {
			Item temp = itemList.get(i);
			if(temp.getCategory().equals(category)) {
				if(selectNum == num++) {
					item = temp;
				}
			}
		}
		return item;
	}
	
	// 카트에 뭐 들었는지 확인
	private boolean CartIsEmty(String logId) {
		int count = 0;
		for(Cart c : cart) {
			if(c.getUserId().equals(logId)) {
				count++;
			}
		}
		if(count == 0) return true;
		else return false;
	}
	
	// 담은 아이템 삭제
	public void deleteItem(String logId) {
		if(CartIsEmty(logId)) {
			System.out.println("[저장된 물품이 없습니다]");
		}
		else {
			for(int i=0; i<cart.size(); i++) {
				if(cart.get(i).getUserId().equals(logId)) {
					System.out.printf("%d) [%s] %d원 (%s) \n",i, cart.get(i).getItem().getItemName(), cart.get(i).getItem().getPrice(), cart.get(i).getItem().getCategory());
				}
			}
			System.out.print("삭제할 item 번호 입력: ");
			String input = Shop.sc.next();
			int sel = -2;
			try {
				sel = Integer.parseInt(input);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			if(sel >= 0 && sel < cart.size()) {
				System.out.printf("[%s 이(가) 장바구니에서 삭제되었습니다]\n",cart.get(sel).getItem().getItemName());
				cart.remove(sel);
			}
		}
	}
	
	// 카트에 아이템 개수 조회
	private int countItems(String logId) {
		int cnt = 0;
		for(Cart c : cart) {
			if(c.getUserId().equals(logId)) { 
				cnt++;
			}
		}
		return cnt;
	}
	
	// 내 카트 조회 
	public void printMyCart(String logId) {
		System.out.println("===" + logId + "님 장바구니 ===");
		if(!CartIsEmty(logId)) {

			// 카운트 및 저장
			int itemCnt = countItems(logId);
			Item tempItem[] = new Item[itemCnt];
			
			int n = 0;
			for(Cart c : cart) {
				if(c.getUserId().equals(logId)) { 
					tempItem[n] = c.getItem();
					n++;
				}
			}
			// 컨트롤
			n = 0;
			Item tempPoint[] = new Item[itemCnt];
			for(int i=0; i<itemCnt; i++) {
				
				boolean check = true;
				for(int k=0; k<itemCnt; k++) {
					if(tempPoint[k] == tempItem[i]) {
						check = false;
					}
				}
				if(check) {
					int tempCnt = 1;
					for(int j=i+1; j<itemCnt; j++) {
						if(tempItem[i] == tempItem[j]) {
								tempCnt++;
							}
					}	
					// 출력
					System.out.printf("[%s]\t%d원(%d원 * %d개) (%s)\n", tempItem[i].getItemName(), (tempItem[i].getPrice() * tempCnt),tempItem[i].getPrice(), tempCnt, tempItem[i].getCategory()); 
					tempPoint[n++] = tempItem[i]; // 넣어준다
				}
				
				
			}

			System.out.println("---------------------"); 
			System.out.println("총 구매 물품 갯수\t: " + itemCnt + "개"); // 총 구매 아이템 개수 출력
			System.out.println("총 비용\t\t: " + sumPrice(logId)+"원"); // 총비용 출력
			System.out.println("---------------------"); 
			
		} else System.out.println("[저장된 물품이 없습니다]");
	}

	// 카트 총 비용 조회
	public int sumPrice(String logId) {
		int sum = 0;
		if(!CartIsEmty(logId)) {
			for(Cart c : cart) {
				if(c.getUserId().equals(logId)) {
					sum += c.getItem().getPrice();
				}
			}			
		}
		return sum;
		
	}

	// 특정 유저 카트 초기화 ////////////////////////////////이닛하면서, 구매된 목록으로 이동
	private void initCart(String logId) {	
		for(Cart c : cart) {
			if(c.getUserId().equals(logId)) {
				payAll.add(c);
				cart.remove(c);
			}
		}
		
		
//		for(int i=0; i<cart.size(); i++) {
//			if(cart.get(i).getUserId().equals(logId)) {
//				cart.remove(i);
//			}
//		}
	}
	// 전체 구매
	public void payAll(String logId) {
		if(!CartIsEmty(logId)) {
			System.out.println("------- 영수증 --------"); 
			printMyCart(logId);
			int sum = sumPrice(logId);
			User user = UserManager.instance.getUser(logId);
			if(user.getMoney() >= sum) {
				int change = user.getMoney() - sum;
				user.setMoney(user.getMoney() - sum);
				initCart(logId);
				System.out.println("잔액		: " + change + "원");
				System.out.println("---------------------"); 
				System.out.println("[결제가 완료되었습니다]");
			}
			else {
				printMyCart(logId);
				System.out.println("[잔액이 부족합니다]");
			}
		}else System.out.println("[장바구니가 비어있습니다]");
	}
	
	
	// =========관리자 메서드===============
	
	// 전체 아이템 출력
	public void printAllItemList() {
		if(itemList.size() != 0) {
			System.out.println("[전체 아이템 출력]");
			for(int i=0; i<itemList.size(); i++) {
				System.out.printf("[%s] %d원 (%s)\n",itemList.get(i).getItemName(), itemList.get(i).getPrice(), itemList.get(i).getCategory());
			}
		}
	}
	
	// 아이템 추가
	public void addItem() {
		while(true) {
			System.out.println("=== 아이템 추가 모드 ===");
			printCategory();
			System.out.println("[뒤로가기: -1]");
			
			// select
			System.out.print("카테고리 선택 : ");
			String input = Shop.sc.next();
			int catIdx = select(input);
			
			if(catIdx == -1) {
				break;
			}
			else if(catIdx != -2) {
				System.out.println("=== 기존 아이템 리스트 ===");
				printItemList(category.get(catIdx));
				System.out.println("[취소: -1]");
				if(catIdx == -1) {
					break;
				}
				else {
					System.out.print("아이템명 입력: ");
					String inName = Shop.sc.next();
					if(getItem(category.get(catIdx), inName) == null) { // 중복아이템 이름
						System.out.print("아이템 가격 입력: ");
						String input2  = Shop.sc.next();
						int inPrice = select(input2);
						if(inPrice >= 0) {
							itemList.add( new Item(inName, inPrice, category.get(catIdx)) );
							System.out.println("[" + inName + "] 추가 완료");
						}
						else System.out.println("올바른 가격 입력필요");
					}
				}
			}
			else System.out.println("카테고리를 다시 확인하세요");
		}
	}
	
	// 아이템 받는 메서드
	private Item getItem(String category, String itemName) {
		Item item = null;
		for(Item i : itemList) {
			if(i.getCategory().equals(category)) {
				if(i.getItemName().equals(itemName)) {
					item = i;
				}
			}
		}
		return item;
	}

	// 아이템 삭제
	public void deleteItem() {
		while(true) {
			System.out.println("--- 아이템 삭제 모드 ---");
			printCategory();
			System.out.println("[뒤로가기: -1]");
			
			// select
			System.out.print("카테고리 선택 : ");
			String input = Shop.sc.next();
			int catIdx = select(input);
			
			if(catIdx == -1) {
				break;
			}
			else if(catIdx != -2) {
				System.out.println("--- 기존 아이템 리스트 ---");
				printItemList(category.get(catIdx));
				System.out.println("[취소: -1]");
				if(catIdx == -1) {
					break;
				}
				else {
					System.out.print("삭제할 인덱스 입력: ");
					input = Shop.sc.next();
					int delIdx = select(input);
					
					Item delItem = selectItem(category.get(catIdx), delIdx);
					if(delItem != null) { // 아이템 있음
						System.out.println("[" + delItem.getItemName() + "] 삭제 완료!\n");
						itemList.remove(delItem);
					}
					else System.out.println("삭제 실패!");
				}
			}
			else System.out.println("카테고리를 다시 확인하세요");
		}
	}

	// 카테고리 전체 출력
	public void printAllCategory() {
		System.out.println("[카테고리 전체 출력]");
		if(!category.isEmpty()) {
			int n = 0;
			for(String c : category) {
				System.out.printf("[%d. %s] \n", n++, c);
			}
		} else System.out.println("저장된 카테고리가 없습니다");
	}

	// 카테고리 추가
	public void addCategory() {
		while(true) {
			printAllCategory();
			System.out.println("--- 카테고리 추가 모드 ---");
			System.out.print("추가 카테고리명 입력[취소: -1]: ");
			String addCat = Shop.sc.next();
			if(!addCat.equals("-1")) {
				if(!category.contains(addCat)) {
					category.add(addCat);
					System.out.println("추가 완료!\n");
				} else System.out.println("중복된 카테고리 입니다");
			}
			else{
				break;
			}
		}
	}
	
	// 카테고리 삭제
	public void deleteCategory() {
		while(true) {
			printAllCategory();
			System.out.println("--- 카테고리 삭제 모드 ---");
			System.out.print("삭제할 인덱스 입력[취소: -1]: ");
			String input = Shop.sc.next();
			int delCatIdx = select(input);
			if(delCatIdx != -1) {
				if(delCatIdx >= 0 && delCatIdx < category.size() ) {
					category.remove(delCatIdx);
					System.out.println("삭제 완료!\n");
				} else System.out.println("인덱스를 확인하세요");
			}
			else if(delCatIdx == -1) {
				break;
			} 
		}
	}
	
	// 카트 전체 출력
	public void printAllCart() {
		System.out.println("[카트 전체 출력] ");
		for(Cart c : cart) {
			System.out.printf("[id: %s] [cat: %s][item: %s, %d원]\n", c.getUserId(),c.getItem().getCategory(), c.getItem().getItemName(), c.getItem().getPrice());
		}
	}
	
	// 카트 추가
	public void addCart() {
		while(true) {
			System.out.println("--- 카트추가 모드 --- ");
			UserManager.instance.printAllUser();
			System.out.print("수정을 원하는 유저 ID입력[취소: -1]: ");
			String userId = Shop.sc.next();
			if(userId.equals("-1")) {
				User user = UserManager.instance.getUser(userId);
				if(user != null) {
					System.out.println("====" + userId + "회원의 카트 ====");
					printMyCart(userId);
					shopping(userId);
					System.out.println("[관리자 권한으로 item을 추가했습니다]");
				} else System.out.println("[없는 유저 입니다]");	
			}
			else {
				break;
			}
		}
	}
	
	// 카트 삭제
	public void deleteCart() {
		while(true) {
			System.out.println("--- 카트삭제 모드 --- ");
			UserManager.instance.printAllUser();
			System.out.print("수정을 원하는 유저 ID입력[삭제: -1]: ");
			String userId = Shop.sc.next();
			if(userId.equals("-1")) {
				User user = UserManager.instance.getUser(userId);
				if(user != null) {
					System.out.println("====" + userId + "회원의 카트 ====");
					printMyCart(userId);
					deleteItem(userId);
					System.out.println("[관리자 권한으로 item을 삭제했습니다]");
				} else System.out.println("[없는 유저 입니다]");
			}
			else {
				break;
			}
		}
	}
	
}
