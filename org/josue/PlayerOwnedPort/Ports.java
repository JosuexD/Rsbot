package PlayerOwnedPort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.SceneObject;

@Manifest(authors = { "Josue" }, name = "POP Minigames", description = "Does the Minigames in Player Owned Ports")
public class Ports extends ActiveScript {
	int patrons[] = { 16529, 16531, 16527, 16537, 16545, 16539, 16535, 16541,
			16523, 16543 };
	private final int AssassinsIDs[] = { 16549, 16548 };
	boolean maid = false;
	private final List<Node> jobsCollection = Collections
			.synchronizedList(new ArrayList<Node>());

	private Tree jobContainer = null;

	@Override
	public void onStart() {
		provide(new GettingBeer());
		provide(new BeerServing());
		provide(new FindingAssasin());
		// provide(new Walking2Bank());
		// provide(new Banking());
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

	public final void provide(final Node... jobs) {
		for (final Node job : jobs) {
			if (!jobsCollection.contains(job)) {
				jobsCollection.add(job);
			}
		}
		jobContainer = new Tree(jobsCollection.toArray(new Node[jobsCollection
				.size()]));
	}

	public class GettingBeer extends Node {

		@Override
		public boolean activate() {
			return !Widgets.get(446, 10).isOnScreen() && maid;
		}

		@Override
		public void execute() {
			SceneObject bar = SceneEntities.getNearest(81302);
			SceneObject secondstairs = SceneEntities.getNearest(81215);
			if (bar != null) {
				if (!bar.isOnScreen()) {
					Walking.walk(bar);
					bar.interact("Get-beer");
					Task.sleep(1000, 1500);
				} else {
					bar.interact("Get-beer");
					Task.sleep(1000, 1500);
				}
			} else if (bar == null && secondstairs != null) {
				secondstairs.interact("Climb down");
				Task.sleep(1000, 1500);

			}

		}

	}

	public class BeerServing extends Node {

		@Override
		public boolean activate() {
			return patrons != null
					&& Players.getLocal().getInteracting() == null
					&& Widgets.get(446, 10).validate() && maid;
		}

		@Override
		public void execute() {
			NPC noobs = NPCs.getNearest(patrons);
			SceneObject stairs = SceneEntities.getNearest(81214);
			if (noobs != null) {
				if (!noobs.isOnScreen()) {
					Walking.walk(noobs);
					noobs.interact("Serve");
					Task.sleep(1000, 1500);
				} else {
					noobs.interact("Serve");
					Task.sleep(1000, 1500);
				}
			} else if (stairs != null && noobs == null) {
				if (!stairs.isOnScreen()) {
					Walking.walk(stairs);
					stairs.interact("Climb up");
					Task.sleep(1000, 1500);
				} else {
					stairs.interact("Climb up");
					Task.sleep(1000, 1500);
				}
			}
		}

	}

	public class FindingAssasin extends Node {

		@Override
		public boolean activate() {

			return AssassinsIDs != null;

		}

		@Override
		public void execute() {
			if (Players.getLocal().getInteracting() == null
					&& !Widgets.get(1184, 14).isOnScreen()) {

				for (int b : AssassinsIDs) {
					final NPC a = NPCs.getNearest(b);
					if (a != null) {
						if (a.isOnScreen()) {
							a.interact("Talk to");
							Task.sleep(1500, 2000);
						} else {
							Walking.walk(a);
							Camera.turnTo(a);
							a.interact("Talk to");
							Task.sleep(1500, 2000);
						}
					}
				}

			}

		}

	}
}