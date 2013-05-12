import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;

import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.map.TilePath;
import org.powerbot.game.api.wrappers.node.SceneObject;

@Manifest(authors = { "Josue" }, name = "Phoenix Plucker", description = "Get's phoenix feathers for you. Uses Dominion Tower for Banking")
public class phoenixplucker extends ActiveScript implements PaintListener {
	/////////////////ID'S/////////////////////////////////
	private final int FOOD = 385;
	private static long startTime = -1;
	// /////////////METHODS/BOOLEANS//////////////////////
	private boolean isAtBank() {
		final SceneObject b = SceneEntities.getNearest(62680);
		return b != null;
	}

	// //////////////AREA/TILES///////////////////////////
	final Area DOMINION_TOWER_AREA = new Area(new Tile[] { new Tile(3334, 3118, 0), new Tile(3389, 3118, 0), new Tile(3389, 3073, 0), 
			new Tile(3333, 3073, 0) });
	final Area DOMINION_TOWER_ENTRANCE = new Area(new Tile[] { new Tile(3367, 3095, 0),
			new Tile(3379, 3095, 0), new Tile(3379, 3086, 0),
			new Tile(3367, 3086, 0) });
	final Tile[] WALKING_TO_PHOENIX = new Tile[] { new Tile(3372, 3091, 0),
			new Tile(3373, 3086, 0), new Tile(3370, 3082, 0),
			new Tile(3365, 3082, 0), new Tile(3360, 3083, 0),
			new Tile(3355, 3082, 0), new Tile(3351, 3085, 0),
			new Tile(3347, 3088, 0), new Tile(3344, 3092, 0),
			new Tile(3340, 3096, 0), new Tile(3339, 3101, 0),
			new Tile(3339, 3106, 0), new Tile(3343, 3109, 0) };

	private final List<Node> jobsCollection = Collections
			.synchronizedList(new ArrayList<Node>());

	private Tree jobContainer = null;

	@Override
	public void onStart() {
		provide(new FeatherPlucking());
		provide(new Banking());
		provide(new Eating());
		provide(new WalkingtoPhoenix());
		provide(new Walkingback());
		startTime = System.currentTimeMillis();
		log.info("All jobs provided");

	}

	@Override
	public int loop() {
		if (jobContainer != null) {
			final Node job = jobContainer.state();
			if (job != null) {
				jobContainer.set(job);
				getContainer().submit(job);
				job.join();
			}
		}
		return Random.nextInt(10, 50);
	}
//what are the jobs?
	public final void provide(final Node... jobs) {
		for (final Node job : jobs) {
			if (!jobsCollection.contains(job)) {
				jobsCollection.add(job);
			}
		}
		jobContainer = new Tree(jobsCollection.toArray(new Node[jobsCollection
				.size()]));
	}

	public class Banking extends Node {

		@Override
		public boolean activate() {
			final SceneObject B = SceneEntities.getNearest(62680);
			return B != null && Inventory.isFull() ;
		}

		@Override
		public void execute() {
			System.out.println("Banking");
			final SceneObject b = SceneEntities.getNearest(62680);//this is the locatable I named it b for short it's for the Bank thing
			//okay what is the >= 7 for? if your within 7 spaces click? Yeah exactly, 7 tiles away kk
			if (Calculations.distanceTo(b) >= 7) { //here where does the distanceTo come into play and how do i set the coordinates from A to B
				//No no, distanceTo() method is used to measure the distance between not really a coordinate but a locatable
				Walking.walk(b);
			}
			if (!Bank.isOpen() && b.isOnScreen()) {
				b.interact("Use");
				Task.sleep(1500, 2300);
			} else if(Bank.isOpen()){
				Bank.depositInventory();
//				if(Players.getLocal().getHealthPercent() < 60){
					Bank.withdraw(FOOD, 1);
//				}
				Bank.close();
			}

		}

	}

	public class WalkingtoPhoenix extends Node {

		@Override
		public boolean activate() {
			return !Inventory.isFull();
		}

		@Override
		public void execute() {
			System.out.println("Walking to Phoenix");
			final SceneObject doorway = SceneEntities.getNearest(62676);
			if (doorway != null && doorway.isOnScreen()) {
				
				doorway.interact("Exit");
			}
			if(doorway != null &&  Calculations.distanceTo(doorway) >= 4){
				Walking.walk(doorway);
			}
			final NPC bird = NPCs.getNearest(1911);
			if (DOMINION_TOWER_AREA.contains(Players.getLocal().getLocation())) {
				TilePath path;
				path = Walking.newTilePath(WALKING_TO_PHOENIX);
				path.traverse();
				Task.sleep(2000, 2500);
			}

		}
	}

	public class FeatherPlucking extends Node {

		@Override
		public boolean activate() {
			final NPC bird = NPCs.getNearest(1911);
			return bird != null && !Inventory.isFull();
		}

		@Override
		public void execute() {
			System.out.println("Plucking feathers");
			final NPC bird = NPCs.getNearest(1911);
			if (bird.isOnScreen()
					&& Players.getLocal().getInteracting() == null) {
				bird.interact("Grab-feather");
				Task.sleep(700, 1500);
			}
			else if (!bird.isOnScreen() && Calculations.distanceTo(bird) <= 7){
				Camera.turnTo(bird);
			}
			else if (Calculations.distanceTo(bird) >= 7){
				Walking.walk(bird);
			}
		}

	}

	public class Walkingback extends Node {

		@Override
		public boolean activate() {
			final NPC bird = NPCs.getNearest(1911);
			//Okay so here we have a wrapper, you have to put it into a wrapper so you can use interact with it. In more simple words.
			//First you find what you want to interact with it's usually a "NPC" or a "SceneObject" (That is exactly how you have to write them), you then name it whatever you want 1911,  is the id? yeah :D.
			//mkay, btw you taken any languages before? html
			// Oh okay then i probbaly will only have to teach you the api i assume right? pretty much, i can decipher almost anything, the methods are still confusing though cause html doesnt really use them
			//Well these are all rsbot api methods for runescape lol yea gotta learn em
			//go to which lines are confusing and ill write notes
			return Inventory.isFull();
		}

		@Override
		public void execute() {
			System.out.println("walking back");
			//The line above is a simple print statement which you may have see nor used before I like to use it to know where I am
			if (!DOMINION_TOWER_ENTRANCE.contains(Players.getLocal().getLocation())) {
				TilePath path;
				path = Walking.newTilePath(WALKING_TO_PHOENIX);
				path.reverse().traverse();
				Task.sleep(2000, 2500);
			}
			//The code above is how I use walking, using the OMU. We can go more into detail later when i make you script lol
			
			////this says climb up the stairs,  rest between 700m and 1500ms yup yup lol
			final SceneObject stairs = SceneEntities.getNearest(62674);
			if (stairs != null && stairs.isOnScreen()){
				stairs.interact("Climb-up");
				Task.sleep(700, 1500);
			}

		}

	}
	//These here are the bottom are the nodes are you probably saw a script is made up of nodes, aka various actions that make the script run.
	public class Eating extends Node{

		@Override
		
		public boolean activate() {
			//This is the activate, here when the conditions are met true it will go to the execute.
			return Inventory.contains(FOOD) && !Bank.isOpen();
		}
		//The conditions I set for this one are if the Inventory has food (meaning sharks) and if the bank is not open (because if it was then we can't eat).
		@Override
		public void execute() {
			//After the activate returns to be true it will do the following: 
			
				System.out.println("Eating");
				Inventory.getItem(FOOD).getWidgetChild().interact("Eat");
				//This is the code used for eating.
				//about the interact() method. You will see it throughout the script inside it you write an action which in runescape it appears in white text. T
				//Some examples include "eat", "climb-up", "use", and others. Yeah exactly this text that you input there is always in white text in runescape
			
			
		}
		
	}
	//Okay this you dont really have to memorize, i mean I havent xD i mostly just copy and paste this part but this is for the graphics part which i assume you dont have much interest since you like to keep it simple xD
	private Image getImage(String url) {
		try {
			return ImageIO.read(new URL(url));
		} catch(IOException e) {
			return null;
		}
	}
	 private final Image img1 = getImage("http://i.imgscalr.com/CSTYjjtTU-S.png");


		public void onRepaint(Graphics render) {
		Graphics2D g = (Graphics2D)render;
		g.drawImage(img1, 385,330, null); //Paint for script		
			final Font font5 = new Font("Ravie", Font.PLAIN, 16);
			render.setFont(font5);
			render.setColor(Color.orange.darker().darker().darker());
			
			//similar to this but with an actions complete thing
			render.drawString(Time.format(System.currentTimeMillis() - startTime),190, 450); // Time Running
			}
}

