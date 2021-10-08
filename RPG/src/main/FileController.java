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

public class FileController {
	FileWriter fw = null;
	FileReader fr = null;
	BufferedReader br = null;
	
	String fileName = "list.txt";
	
	File file = new File(fileName);
	
	public static FileController instance = new FileController(); 
	
	private FileController() {

	}
	
	public void save() {
		// player
		try {
			fw = new FileWriter(file);
			
			Player player = PlayerController.instance.getPlayer();
			String writeP = "";
			
			writeP += player.getPlayerName()+"/"; // 이름
			writeP += player.getMoney() + "\n"; // 돈
			
			writeP += player.getInventory().size() + "\n"; // 인벤토리 아이템 개수
			
			for(Item item : player.getInventory()) { 
				writeP += item.getKind()+"/"; 
				writeP += item.getName()+"/"; 
				writeP += item.getPower()+"/"; 
				writeP += item.getPrice() + "\n";  
			}
			
			fw.write(writeP);
//			private String name;
//			private int level;
//			boolean isParty;
//			
//			int str;
//			int def;
//			int hp;
//			
//			Item weapon;
//			Item armor;
//			Item accessory;			
									
			// unit			
			String writeU = "";

			GuildController gc = GuildController.instance;
			writeU += gc.sizeOfGuild();
			for(int i=0; i<gc.sizeOfGuild(); i++) {
				writeU += gc.getGuildUnit(i).getName() +"/";
				writeU += gc.getGuildUnit(i).getLevel() +"/";
				writeU += gc.getGuildUnit(i).isParty() +"/";
				writeU += gc.getGuildUnit(i).getStr() +"/";
				writeU += gc.getGuildUnit(i).getDef() +"/";
				writeU += gc.getGuildUnit(i).getHp() +"/n";
				
				// 아이템...
				if(gc.getGuildUnit(i).getWeapon() != null) {
					writeU += Item.WEAPON;
					writeU += gc.getGuildUnit(i).getWeapon().getKind()+"/";
					writeU += gc.getGuildUnit(i).getWeapon().getName()+"/";
					writeU += gc.getGuildUnit(i).getWeapon().getPower()+"/";
					writeU += gc.getGuildUnit(i).getWeapon().getPrice()+"/";
				}
				if(gc.getGuildUnit(i).getArmor() != null) {
					writeU += Item.ARMOR;
					writeU += gc.getGuildUnit(i).getArmor().getKind()+"/";
					writeU += gc.getGuildUnit(i).getArmor().getName()+"/";
					writeU += gc.getGuildUnit(i).getArmor().getPower()+"/";
					writeU += gc.getGuildUnit(i).getArmor().getPrice()+"/";
				}
				if(gc.getGuildUnit(i).getAccessory() != null) {
					writeU += Item.ACCESSORY;
					writeU += gc.getGuildUnit(i).getAccessory().getKind()+"/";
					writeU += gc.getGuildUnit(i).getAccessory().getName()+"/";
					writeU += gc.getGuildUnit(i).getAccessory().getPower()+"/";
					writeU += gc.getGuildUnit(i).getAccessory().getPrice()+"/";
				}
			}
			
			fw.write(writeP);
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		
	}
	
	public void load() {
		Player player = PlayerController.instance.getPlayer();

		File file = null;
		FileReader reader = null;
		BufferedReader br = null;
		String path = "game.txt";
		file = new File(path);

		if (file.exists()) {
			reader = new FileReader(path);
			br = new BufferedReader(reader);
			String money = br.readLine();
			
			player.plusMoney(Integer.parseInt(money)); 

			String guildSize = br.readLine();

			int size = Integer.parseInt(guildSize);
			
			for(int i=0; i < size; i++) {
				String unitData = br.readLine();
				String[] unitArr = unitData.split("/");
				String name = unitArr[0];
				int level = Integer.parseInt(unitArr[1]);
				int maxhp = Integer.parseInt(unitArr[2]);
				int att = Integer.parseInt(unitArr[3]);
				int def = Integer.parseInt(unitArr[4]);
				int exp = Integer.parseInt(unitArr[5]);
				boolean party = Boolean.parseBoolean(unitArr[6]);
				Unit temp = new Unit(name ,level ,maxhp ,  att , def ,exp , party );
				player.guild.guildList.add(temp);
				//==================== item =======================
				String item = br.readLine();			
				String itemArr[] = item.split("/");	
				if(itemArr[0].equals("null")) {
					player.get().get(i).weapon = null;						
				}
				else {			
					String[] weapon = itemArr[0].split(",");
					int itemKind = Integer.parseInt(weapon[0]);
					String itemName = weapon[1];
					int itemPower =  Integer.parseInt(weapon[2]);
					int itemPrice =  Integer.parseInt(weapon[3]);
					Item item = new Item();
					item.setItem(itemKind , itemName , itemPower , itemPrice);
					Player.getGuildList().get(i).weapon = item;
				}
				if(itemArr[1].equals("null")) {
					Player.getGuildList().get(i).armor = null;
				}
				else {
					String[] armor = itemArr[1].split(",");
					int itemKind = Integer.parseInt(armor[0]);	
					String itemName = armor[1];
					int itemPower =  Integer.parseInt(armor[2]);
					int itemPrice =  Integer.parseInt(armor[3]);
					Item item = new Item();
					item.setItem(itemKind , itemName , itemPower , itemPrice);
					Player.getGuildList().get(i).armor = item;
				}
				if(itemArr[2].equals("null")) {
					Player.getGuildList().get(i).ring = null;
				}
				else {
					String[] ring = itemArr[2].split(",");
					int itemKind = Integer.parseInt(ring[0]);
					String itemName = ring[1];
					int itemPower =  Integer.parseInt(ring[2]);
					int itemPrice =  Integer.parseInt(ring[3]);
					Item item = new Item();
					item.setItem(itemKind , itemName , itemPower , itemPrice);
					Player.getGuildList().get(i).ring = item;
				}
				
			}	
		}
	
	}


	
}
