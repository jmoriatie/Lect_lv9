package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import player.Player;
import player.PlayerController;
import shop.Item;
import unit.GuildController;
import unit.Unit;

public class FileController {
	
	String fileName = "game.txt";
	File file = new File(fileName);	

	public static FileController instance = new FileController();

	private FileController() {

	}

	public void save(){
		FileWriter fw = null;

		// player
		try {
			fw = new FileWriter(file);

			Player player = PlayerController.instance.getPlayer();
			String write = "";
			// 플레이어: 이름/돈
			// 인벤토리: 아이템 개수// 종류/이름/능력치/가격
			write += player.getPlayerName() + "/"; // 이름
			write += player.getMoney() + "\n"; // 돈

			write += player.getInventory().size() + "\n"; // 인벤토리 아이템 개수

			for (Item item : player.getInventory()) {
				write += item.getKind() + "/";
				write += item.getName() + "/";
				write += item.getPower() + "/";
				write += item.getPrice() + "\n";
			}
			
			write += GuildController.instance.sizeOfGuild() + "\n";
			if (GuildController.instance.sizeOfGuild() > 0) {
				for (int i = 0; i < GuildController.instance.sizeOfGuild(); i++) {
					// guild
					write += GuildController.instance.getGuildUnit(i).getName() + "/";
					write += GuildController.instance.getGuildUnit(i).getLevel() + "/";
					write += GuildController.instance.getGuildUnit(i).isParty() + "/"; // 불러올 때 구분
					write += GuildController.instance.getGuildUnit(i).getStr() + "/";
					write += GuildController.instance.getGuildUnit(i).getDef() + "/";
					write += GuildController.instance.getGuildUnit(i).getHp() + "/";

					// 아이템
					if (GuildController.instance.getGuildUnit(i).getWeapon() != null) {
						write += Item.WEAPON + "/";
						write += GuildController.instance.getGuildUnit(i).getWeapon().getName() + "/";
						write += GuildController.instance.getGuildUnit(i).getWeapon().getPower() + "/";
						write += GuildController.instance.getGuildUnit(i).getWeapon().getPrice() + "/";
					}
					if (GuildController.instance.getGuildUnit(i).getArmor() != null) {
						write += Item.ARMOR + "/";
						write += GuildController.instance.getGuildUnit(i).getArmor().getName() + "/";
						write += GuildController.instance.getGuildUnit(i).getArmor().getPower() + "/";
						write += GuildController.instance.getGuildUnit(i).getArmor().getPrice() + "/";
					}
					if (GuildController.instance.getGuildUnit(i).getAccessory() != null) {
						write += Item.ACCESSORY + "/";
						write += GuildController.instance.getGuildUnit(i).getAccessory().getName() + "/";
						write += GuildController.instance.getGuildUnit(i).getAccessory().getPower() + "/";
						write += GuildController.instance.getGuildUnit(i).getAccessory().getPrice() + "/";
					}
					write += "\n";
				}
			}

			// 길드원: 길드원 수// 이름/레벨/파티여부/힘/방어/체력/착용템종류/착용템명/착용템능력치/착용템가격
			// 파티원: 파티원 수// 이름/레벨/파티여부/힘/방어/체력/착용템종류/착용템명/착용템능력치/착용템가격
			write += GuildController.instance.sizeOfParty() + "\n";
			if (GuildController.instance.sizeOfParty() > 0) {
				for (int i = 0; i < GuildController.instance.sizeOfParty(); i++) {
					// guild
					write += GuildController.instance.getPartydUnit(i).getName() + "/";
					write += GuildController.instance.getPartydUnit(i).getLevel() + "/";
					write += GuildController.instance.getPartydUnit(i).isParty() + "/"; // 불러올 때 구분
					write += GuildController.instance.getPartydUnit(i).getStr() + "/";
					write += GuildController.instance.getPartydUnit(i).getDef() + "/";
					write += GuildController.instance.getPartydUnit(i).getHp() + "/";

					// 아이템
					if (GuildController.instance.getPartydUnit(i).getWeapon() != null) {
						write += Item.WEAPON + "/";
						write += GuildController.instance.getPartydUnit(i).getWeapon().getName() + "/";
						write += GuildController.instance.getPartydUnit(i).getWeapon().getPower() + "/";
						write += GuildController.instance.getPartydUnit(i).getWeapon().getPrice() + "/";
					}
					if (GuildController.instance.getPartydUnit(i).getArmor() != null) {
						write += Item.ARMOR + "/";
						write += GuildController.instance.getPartydUnit(i).getArmor().getName() + "/";
						write += GuildController.instance.getPartydUnit(i).getArmor().getPower() + "/";
						write += GuildController.instance.getPartydUnit(i).getArmor().getPrice() + "/";
					}
					if (GuildController.instance.getPartydUnit(i).getAccessory() != null) {
						write += Item.ACCESSORY + "/";
						write += GuildController.instance.getPartydUnit(i).getAccessory().getName() + "/";
						write += GuildController.instance.getPartydUnit(i).getAccessory().getPower() + "/";
						write += GuildController.instance.getPartydUnit(i).getAccessory().getPrice() + "/";
					}
					write += "\n";
				}
			}
//			SYSTEM.OUT.PRINTLN("--확인용--");
//			SYSTEM.OUT.PRINTLN(WRITE);
			fw.write(write);

			fw.close();
			System.out.println("[저장 완료]");

		} catch (IOException e) {
			System.out.println("[저장 실패]");
			e.printStackTrace();
		}
	}

	public void load() throws IOException {

		if(file.exists()) {
			PlayerController pc = PlayerController.instance;
			GuildController gc = GuildController.instance;
			
			FileReader fr = null;
			BufferedReader br = null;
			
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			// 플레이어: 이름/돈
			// 인벤토리: 아이템 개수// 종류/이름/능력치/가격
			String tempPlayer[] = br.readLine().split("\n");
			// 플레이어 기초정보
			String tempArr[] = tempPlayer[0].split("/");
			pc.p1 = new Player( tempArr[0]); // PlayerController의 플레이어를 p1으로 직접 셋팅
			int money = Integer.parseInt(tempArr[1]);
			pc.getPlayer().plusMoney(money - 10000); // 기존에 있던 돈 빼주고, 저장된 돈 더해주기
			// 인벤토리
			int size = Integer.parseInt( br.readLine() );
			if(size > 0) {
				for(int i=0; i<size; i++) {
					tempArr = br.readLine().split("/");
					int kind = Integer.parseInt(tempArr[0]);
					String name = tempArr[1];
					int power = Integer.parseInt(tempArr[2]);
					int price = Integer.parseInt(tempArr[3]);
					pc.getPlayer().setInventory( new Item(kind, name, power, price) );
				}
			}
			
			gc.initUnitArr(); // 길드, 파티 초기화
			// 길드원: 길드원 수// 이름/레벨/파티여부/힘/방어/체력/
			size = Integer.parseInt( br.readLine() ); // 길드원 수
			if(size > 0) {
				for(int i=0; i<size; i++) {
					tempArr = br.readLine().split("/");
					String name = tempArr[0];
					int level = Integer.parseInt( tempArr[1] );
//					boolean isParty = false;
					int str = Integer.parseInt( tempArr[3] );
					int def = Integer.parseInt( tempArr[4] );
					int hp = Integer.parseInt( tempArr[5] );
					Unit unit = new Unit(name, level, false, str, def, hp); 
					gc.setGuildUnit(unit);
					// 아이템 있을 때
					if(tempArr.length > 5) {
						//착용템종류/착용템명/착용템능력치/착용템가격
						// 6  7  8  9	2 3 0 1
						// 10 11 12 13	2 3 0 1    
						// 14 15 16 17	2 3 0 1
						for(int j=6; j<tempArr.length; j+=4) { // 6~ 있을 때까지 반복
							int kind = Integer.parseInt(tempArr[j]);
							String itemName = tempArr[j+1];
							int itemPower = Integer.parseInt(tempArr[j+2]);
							int itemPrice = Integer.parseInt(tempArr[j+3]);
							Item item = new Item(kind, itemName, itemPower, itemPrice);

							if(kind == Item.WEAPON) unit.setWeapon(item); // 해당 유닛에 주소값 공유가 될 것인가!!!!!!!!
							else if(kind == Item.ARMOR) unit.setArmor(item);
							else if(kind == Item.ACCESSORY) unit.setAccessory(item);
						}
					}
				}
			}

			// 파티원: 파티원 수// 이름/레벨/파티여부/힘/방어/체력/
			size = Integer.parseInt( br.readLine() ); // 파티원 수
			if(size > 0) {
				for(int i=0; i<size; i++) {
					tempArr = br.readLine().split("/");
					String name = tempArr[0];
					int level = Integer.parseInt( tempArr[1] );
//					boolean isParty = Boolean.parseBoolean(tempArr[2]);
					int str = Integer.parseInt( tempArr[3] );
					int def = Integer.parseInt( tempArr[4] );
					int hp = Integer.parseInt( tempArr[5] );
					Unit unit = new Unit(name, level, true, str, def, hp); 
					gc.setPartyUnit(unit);
					// 아이템 있을 때
					if(tempArr.length > 5) {
						//착용템종류/착용템명/착용템능력치/착용템가격
						for(int j=6; j<tempArr.length; j+=4) { 
							int kind = Integer.parseInt(tempArr[j]);
							String itemName = tempArr[j+1];
							int itemPower = Integer.parseInt(tempArr[j+2]);
							int itemPrice = Integer.parseInt(tempArr[j+3]);
							Item item = new Item(kind, itemName, itemPower, itemPrice);

							if(kind == Item.WEAPON) unit.setWeapon(item);
							else if(kind == Item.ARMOR) unit.setArmor(item);
							else if(kind == Item.ACCESSORY) unit.setAccessory(item);
						}
					}
				}
			}
			
			br.close();
			fr.close();
		} else System.out.println("[저장 파일이 없습니다]");
	}

}
