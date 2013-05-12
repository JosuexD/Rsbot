package org.josue.jdrags.core;

/**
 * Created with IntelliJ IDEA.
 * User: Josue
 * Date: 5/11/13
 * Time: 4:35 PM
 * To change this template use File | Settings | File Templates.
 */

import org.josue.jdrags.method.Methods;
import org.josue.jdrags.nodes.*;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Manifest(authors = {"Josue"}, name = "JDrags", description = "Kills Black Dragons in Evil Chicken Lair")
public class Main extends ActiveScript implements PaintListener {
    private final List<Node> jobsCollection = Collections
            .synchronizedList(new ArrayList<Node>());

    private Tree jobContainer = null;

    @Override
    public void onStart() {
        provide(new GettingChicken());
        provide(new Banker());
        provide(new QuickPrayers());
        provide(new Abilities());
        provide(new Drinking());
        provide(new Alching());
        provide(new Eating());
        provide(new WalkingtoBank());
        provide(new WalkingtoDragons());
        provide(new Looting());
        provide(new Antifire());
        provide(new Attack());
        Mouse.setSpeed(Mouse.Speed.FAST);
        Methods.startTime = System.currentTimeMillis();
        Methods.startxp = Skills.getExperience(Skills.STRENGTH);
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

    private static final DecimalFormat df = new DecimalFormat("###,###,###,###");

    private Image getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch (IOException e) {
            return null;
        }
    }

    private final Image img1 = getImage("http://i.imgscalr.com/VAAMUCNnE-S.png");

    @Override
    public void onRepaint(Graphics g) {
        final Font font5 = new Font("Ravie", Font.PLAIN, 16);
        g.setFont(font5);
        g.setColor(Color.BLACK.darker().darker());
        g.drawString("Time ran: " + Time.format(System.currentTimeMillis() - Methods.startTime), 10, 70); // TIME RUNNING
        g.drawString("Strength XP: " + df.format((Skills.getExperience(Skills.STRENGTH) - Methods.startxp)), 10, 85); // EXPERIENCE GAINED
        g.drawString("Strength XP/h: " + df.format(Methods.getEXPperHour()), 10, 100);// EXPERIENCE GAINED PER HOUR
        g.drawImage(img1, 380, 300, null); // Paint for script
    }
}
