package de.kizzyjune;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.*;

@SuppressWarnings("JavaPrintToLogpoint")
public final class WeDoNotBreakUserspace {
    private static volatile boolean print = true;
    @SuppressWarnings({"DataFlowIssue", "ResultOfMethodCallIgnored"})
    static void main() throws InterruptedException {
        final InputStream is = WeDoNotBreakUserspace.class.getResourceAsStream("/resources/throwable.wav");
        if (is == null) {
            System.err.println("Paint sample missing, this is unacceptable");
            System.exit(1);
        }
        final String[] files = {"ascii.png","knock.wav","whisper.wav"};
        int index = 0;
        while (index != files.length) {
            final InputStream is2 = WeDoNotBreakUserspace.class.getResourceAsStream("/resources/" + files[index]);
            if (is2 == null) {
                handleThrowable(new FileNotFoundException("Required file " + files[index] + " is missing from the JAR!"));
            }
            index++;
        }
        IO.println("okay, now let's get one thing straight");
        Thread.sleep(3000);
        final JFrame window = new JFrame("We do not break userspace");
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // No way out unless you let the JVM terminate itself at the end of execution
        window.setSize(1920, 1080 / 2);
        window.setLocationRelativeTo(null);
        window.getContentPane().setBackground(Color.BLACK);
        try {
            final Image icon = ImageIO.read(WeDoNotBreakUserspace.class.getResourceAsStream("/resources/icon.jpg"));
            window.setIconImage(icon);
        } catch (final Throwable t) {
            if (t.getMessage().equals("input == null!")) {
                System.err.println("Window icon missing from the JAR!");
                System.err.println("Press enter to acknowledge this and continue anyway.");
                try {
                    System.in.read();
                } catch (final Throwable t2) {
                    handleThrowable(t2);
                }
            } else {
                handleThrowable(t);
            }
        }
        final String urlString = "/resources/ascii.png";
        final URL imgUrl = WeDoNotBreakUserspace.class.getResource(urlString);
        if (imgUrl == null) {
            handleThrowable(new FileNotFoundException("Resource " + urlString + " not found inside JAR"));
        }
        final ImageIcon img = new ImageIcon(imgUrl);
        final JLabel label = new JLabel(img);
        label.setVisible(true);
        window.add(label, BorderLayout.CENTER);
        window.setVisible(true);
        sched1();
        while (print) IO.println(ThreadLocalRandom.current().nextBoolean() ? "We do not break userspace" : "We do not break userspace.");
        window.setVisible(false);
        Thread.sleep(7000);
        try {
            final AudioInputStream aus = AudioSystem.getAudioInputStream(WeDoNotBreakUserspace.class.getResourceAsStream("/resources/whisper.wav"));
            final Clip clip = AudioSystem.getClip();
            clip.open(aus);
            clip.start();
        } catch (final Throwable t) {
            handleThrowable(t);
        }
        Thread.sleep(3000);
        try {
            final AudioInputStream aus = AudioSystem.getAudioInputStream(WeDoNotBreakUserspace.class.getResourceAsStream("/resources/knock.wav"));
            final Clip clip = AudioSystem.getClip();
            clip.open(aus);
            clip.start();
        } catch (final Throwable t) {
            handleThrowable(t);
        }
        Thread.sleep(5000);
        System.exit(0);
    }

    @SuppressWarnings({"CallToPrintStackTrace", "SameParameterValue", "DataFlowIssue"})
    private static void handleThrowable(final Throwable t) throws InterruptedException {
        final boolean isError = (t instanceof Error);
        final boolean isException = (t instanceof Exception);
        final boolean justThrowable = (!isError && !isException);
        final StringBuilder sb = new StringBuilder();
        String type;
        if (!justThrowable) type = isError ? "error" : "exception";
        else type = "throwable";
        sb.append("An ");
        sb.append(type);
        sb.append(" has occurred");
        sb.append("\n");
        try {
            final AudioInputStream aus = AudioSystem.getAudioInputStream(WeDoNotBreakUserspace.class.getResourceAsStream("/resources/throwable.wav"));
            final Clip clip = AudioSystem.getClip();
            clip.open(aus);
            clip.start();
            System.err.println("no");
        } catch (final Throwable ignored) {
        }
        System.err.println(sb);
        t.printStackTrace();
        Thread.sleep(2000);
        System.exit(1);
    }
    private static void sched1() {
            final ThreadFactory threadFactory = r -> {
                final Thread thread = new Thread(r, "Window closing scheduler thread");
                thread.setDaemon(true);
                return thread;
            };

            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1, threadFactory);

            final Runnable task = () -> print = false;

            scheduler.schedule(task, 10, TimeUnit.SECONDS);

        }
}