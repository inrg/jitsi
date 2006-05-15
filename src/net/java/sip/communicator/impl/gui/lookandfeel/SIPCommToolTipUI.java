/*
 * SIP Communicator, the OpenSource Java VoIP and Instant Messaging client.
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package net.java.sip.communicator.impl.gui.lookandfeel;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicToolTipUI;

import net.java.sip.communicator.impl.gui.utils.AntialiasingManager;

/**
 * SIPCommToolTipUI implementation.
 * 
 * @author Yana Stamcheva
 */
public class SIPCommToolTipUI extends BasicToolTipUI {

    public static ComponentUI createUI(JComponent c) {
        return new SIPCommToolTipUI();
    }
    
    public void paint(Graphics g, JComponent c) {        
        AntialiasingManager.activateAntialiasing(g);
        super.paint(g, c);        
    }
}
