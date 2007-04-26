/*
 * SIP Communicator, the OpenSource Java VoIP and Instant Messaging client.
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */

package net.java.sip.communicator.impl.gui.main.configforms;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.imageio.*;
import javax.swing.*;

import net.java.sip.communicator.impl.gui.customcontrols.*;
import net.java.sip.communicator.impl.gui.i18n.*;
import net.java.sip.communicator.impl.gui.main.*;
import net.java.sip.communicator.service.gui.*;

/**
 * The implementation of the <tt>ConfigurationManager</tt> interface.
 * 
 * @author Yana Stamcheva
 */
public class ConfigurationFrame
    extends SIPCommDialog
    implements  ConfigurationWindow
{
    private ConfigFormList configList;

    private TitlePanel titlePanel = new TitlePanel();

    private JPanel mainPanel = new JPanel(new BorderLayout());
    
    private JPanel centerPanel = new JPanel(new BorderLayout());
    
    private JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    
    private I18NString closeString = Messages.getI18NString("close");
    
    private JButton closeButton = new JButton(closeString.getText());

    private MainFrame mainFrame;

    /**
     * Creates an instance of <tt>ConfigurationManagerImpl</tt>.
     * 
     * @param mainFrame The main application window.
     */
    public ConfigurationFrame(MainFrame mainFrame) {
        
        super(mainFrame);
       
        this.mainFrame = mainFrame;
        
        this.configList = new ConfigFormList(this);

        this.setTitle(Messages.getI18NString("configuration").getText());
        
        this.getContentPane().setLayout(new BorderLayout());

        this.addDefaultForms();

        this.mainPanel.add(centerPanel, BorderLayout.CENTER);

        this.mainPanel.add(configList, BorderLayout.WEST);
        
        this.buttonsPanel.add(closeButton);
        
        this.closeButton.setMnemonic(closeString.getMnemonic());
        
        this.closeButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });
        
        this.mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
        
        this.getContentPane().add(mainPanel);
    }

    /**
     * Some configuration forms constructed from the ui implementation itself
     * are added here in the configuration dialog.
     */
    public void addDefaultForms()
    {
        this.addConfigurationForm(
                new AccountsConfigurationForm(mainFrame));        
    }

    /**
     * Implements the <code>ConfigurationManager.addConfigurationForm</code>
     * method. Checks if the form contained in the <tt>ConfigurationForm</tt>
     * is an instance of java.awt.Component and if so adds the form in this
     * dialog, otherwise throws a ClassCastException.
     * @see ConfigurationWindow#addConfigurationForm(ConfigurationForm)
     */
    public void addConfigurationForm(ConfigurationForm configForm) {

        if(configForm.getForm() instanceof Component)
        {
            this.configList.addConfigForm(configForm);
    
//            this.recalculateSize();
            
        }
        else {
            throw new ClassCastException("ConfigurationFrame :"
            + configForm.getForm().getClass()
            + " is not a class supported by this ui implementation");
        }
    }

    /**
     * Implements <code>ConfigurationManager.removeConfigurationForm</code>
     * method. Removes the given <tt>ConfigurationForm</tt> from this
     * dialog.
     * @see ConfigurationWindow#removeConfigurationForm(ConfigurationForm)
     */
    public void removeConfigurationForm(ConfigurationForm configForm)
    {
        this.configList.removeConfigForm(configForm);
    }

    public void showFormContent(ConfigurationForm configForm)
    {
        this.centerPanel.removeAll();
        
        this.titlePanel.setTitleText(configForm.getTitle());

        this.centerPanel.add(titlePanel, BorderLayout.NORTH);

        this.centerPanel.add((Component)configForm.getForm(),
                BorderLayout.CENTER);
        
        this.centerPanel.revalidate();
        this.centerPanel.repaint();
     
    }
    
    
    /**
     * Calculates the size of the frame depending on the size of the largest
     * contained form.
     */
//    public void recalculateSize()
//    {
//        double width = 0;
//
//        double height = 0;
//
//        for (int i = 0; i < configList.getSize(); i++)
//        {
//            ConfigurationForm configForm = (ConfigurationForm) configContainer
//                    .get(i);
//            
//            Component form = (Component)configForm.getForm();
//            if (width < form.getPreferredSize().getWidth())
//                width = form.getPreferredSize().getWidth();
//
//            if (height < form.getPreferredSize().getHeight())
//                height = form.getPreferredSize().getHeight();
//        }
//     
//        this.mainPanel.setPreferredSize(new Dimension(
//            (int) width + 150, (int) height + 100));
//    }

    /**
     * Implements <code>ApplicationWindow.show</code> method.
     * @see net.java.sip.communicator.service.gui.ExportedWindow#showWindow()
     */
    public void setVisible(boolean isVisible)
    {
        if(isVisible)
        {
            this.configList.setSelectedIndex(0);
            
            super.setVisible(true);
            
            this.closeButton.requestFocus();
        }
        else
            super.setVisible(false);
    }

    /**
     * Implements <code>ApplicationWindow.minimizeWindow</code> method.
     * @see net.java.sip.communicator.service.gui.ExportedWindow#minimizeWindow()
     */
    public void minimize()
    {}

    /**
     * Implements <code>ApplicationWindow.maximizeWindow</code> method.
     * @see net.java.sip.communicator.service.gui.ExportedWindow#maximizeWindow()
     */
    public void maximize()
    {}
    
    /**
     * Implements <tt>SIPCommFrame.close()</tt> method. Performs a click on the
     * close button.
     */
    protected void close(boolean isEscaped)
    {
        this.closeButton.doClick();
    }

    /**
     * Returns the identifier of this <tt>ExportedWindow</tt>.
     */
    public WindowID getIdentifier()
    {
        return ExportedWindow.CONFIGURATION_WINDOW;
    } 
    
    /**
     * Implements the <tt>ExportedWindow.bringToFront</tt> method. Brings this
     * window to front.
     */
    public void bringToFront()
    {
        this.toFront();
    }
}
