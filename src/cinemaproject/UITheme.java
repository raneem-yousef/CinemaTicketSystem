/*
 * Centralized "Burgundy" visual theme for the whole application.
 * Installing the theme (installLookAndFeel) re-colors the Nimbus Look & Feel
 * so every screen (Login, Admin, Customer, dialogs...) shares the same
 * dark / burgundy palette without having to touch each generated form by hand.
 * The style* helpers are small extra touches applied to a handful of key
 * components (titles, primary buttons) on top of the global palette.
 */
package cinemaproject;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

public final class UITheme {

    private UITheme() {
    }

    // ---- Palette -------------------------------------------------------
    public static final Color BG_DARK        = new Color(0x25, 0x25, 0x25); // main dark background
    public static final Color BG_DARK_2      = new Color(0x2E, 0x2E, 0x2E); // fields / tables / cards on dark bg
    public static final Color SURFACE        = new Color(0x3D, 0x3D, 0x3D); // secondary neutral chrome
    public static final Color BURGUNDY       = new Color(0x80, 0x00, 0x20); // primary accent
    public static final Color BURGUNDY_LIGHT = new Color(0xC2, 0x4D, 0x67); // hover / focus accent
    public static final Color CREAM          = new Color(0xF2, 0xF0, 0xEB); // primary text on dark bg
    public static final Color MUTED          = new Color(0xA6, 0xA6, 0xA0); // secondary / disabled text
    public static final Color WHITE_CARD     = new Color(0xFF, 0xFF, 0xFF);
    public static final Color DARK_TEXT      = new Color(0x25, 0x25, 0x25); // text on light surfaces

    private static final String FONT_FAMILY = "Segoe UI";

    /**
     * Installs Nimbus and re-colors it to the burgundy palette. Safe to call
     * more than once (each form's own main() calls it) - later calls are
     * cheap no-ops once Nimbus is already active.
     */
    public static void installLookAndFeel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            // If Nimbus isn't available we simply keep the platform default L&F.
        }

        UIManager.put("control", new ColorUIResource(BG_DARK));
        UIManager.put("nimbusBase", new ColorUIResource(BURGUNDY));
        UIManager.put("nimbusBlueGrey", new ColorUIResource(SURFACE));
        UIManager.put("nimbusFocus", new ColorUIResource(BURGUNDY_LIGHT));
        UIManager.put("nimbusLightBackground", new ColorUIResource(BG_DARK_2));
        UIManager.put("nimbusSelectionBackground", new ColorUIResource(BURGUNDY));
        UIManager.put("nimbusSelectedText", new ColorUIResource(CREAM));
        UIManager.put("nimbusDisabledText", new ColorUIResource(MUTED));
        UIManager.put("nimbusInfoBlue", new ColorUIResource(BURGUNDY_LIGHT));
        UIManager.put("info", new ColorUIResource(SURFACE));
        UIManager.put("text", new ColorUIResource(CREAM));
        UIManager.put("controlText", new ColorUIResource(CREAM));
        UIManager.put("menuText", new ColorUIResource(CREAM));
        UIManager.put("infoText", new ColorUIResource(CREAM));

        Font base = new Font(FONT_FAMILY, Font.PLAIN, 13);
        UIManager.put("defaultFont", new FontUIResource(base));
    }

    // ---- Component helpers ----------------------------------------------

    /** Large bold title/heading label, e.g. screen name at the top of a form. */
    public static void styleHeading(JLabel label) {
        label.setForeground(CREAM);
        label.setFont(new Font(FONT_FAMILY, Font.BOLD, 20));
    }

    /** Smaller muted caption/sub-label, e.g. "Sign in to continue". */
    public static void styleCaption(JLabel label) {
        label.setForeground(MUTED);
        label.setFont(new Font(FONT_FAMILY, Font.PLAIN, 12));
    }

    /** Ordinary field caption ("Username :", "Price", ...). */
    public static void styleFieldLabel(JLabel label) {
        label.setForeground(MUTED);
        label.setFont(new Font(FONT_FAMILY, Font.PLAIN, 13));
    }

    /** Main call-to-action button (Login, Confirm, Insert, Create...). */
    public static void stylePrimaryButton(JButton button) {
        button.setBackground(BURGUNDY);
        button.setForeground(CREAM);
        button.setFont(new Font(FONT_FAMILY, Font.BOLD, 13));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /** Secondary / neutral button (Clear, Read, Search, navigation menu tiles...). */
    public static void styleSecondaryButton(JButton button) {
        button.setBackground(SURFACE);
        button.setForeground(CREAM);
        button.setFont(new Font(FONT_FAMILY, Font.PLAIN, 13));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /** Destructive / cancel / logout button. */
    public static void styleDangerButton(JButton button) {
        button.setBackground(new Color(0x5A, 0x1A, 0x1A));
        button.setForeground(CREAM);
        button.setFont(new Font(FONT_FAMILY, Font.PLAIN, 13));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /** A quiet text-link style button ("New User ?", "Cancel"). */
    public static void styleLinkButton(JButton button) {
        button.setForeground(BURGUNDY_LIGHT);
        button.setFont(new Font(FONT_FAMILY, Font.PLAIN, 12));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
    }
}
