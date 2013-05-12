package org.josue.ZDrags.core;

/**
 * Created with IntelliJ IDEA.
 * User: Josue
 * Date: 5/11/13
 * Time: 4:35 PM
 * To change this template use File | Settings | File Templates.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.josue.ZDrags.nodes.Antifire.DrinkAntire;
import org.josue.ZDrags.nodes.WalkingtoDragons.DragonWalking;
import org.josue.ZDrags.nodes.Abilities.UseAbilities;
import org.josue.ZDrags.nodes.Alching.Alch;
import org.josue.ZDrags.nodes.Attack.Attacking;
import org.josue.ZDrags.nodes.Banker.Banking;
import org.josue.ZDrags.nodes.ChickenKill.GetChicken;
import org.josue.ZDrags.nodes.Drinking.Drink;
import org.josue.ZDrags.nodes.Eating.Eat;
import org.josue.ZDrags.nodes.Looting.Loot;
import org.josue.ZDrags.nodes.QuickPrayers.Quickprayer;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;

import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.util.Random;
import org.josue.ZDrags.nodes.WalkingtoBank.BankWalking;


@Manifest(authors = { "Josue" }, name = "ZDrags", description = "Kills Black Dragons in Evil Chicken Lair")
public class Main extends ActiveScript {
    private final List<Node> jobsCollection = Collections
            .synchronizedList(new ArrayList<Node>());

    private Tree jobContainer = null;

    @Override
    public void onStart() {

        provide(new GetChicken());
        provide(new Banking());
        provide(new BankWalking());
        provide(new DragonWalking());
        provide(new Loot());
        provide(new Quickprayer());
        provide(new DrinkAntire());
        provide(new Attacking());
        provide(new UseAbilities());
        provide(new Drink());
        provide(new Alch());
        provide(new Eat());

        Mouse.setSpeed(Mouse.Speed.FAST);

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


}
