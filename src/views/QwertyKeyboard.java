package views;

import code.ButtonClickListener;
import code.interfaces.IController;
import code.interfaces.IModel;

import javax.swing.*;
import java.awt.*;

public class QwertyKeyboard extends JPanel {

    //Individual keyboard rows
    private String[] firstRow = {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"};
    private String[] secondRow = {"A", "S", "D", "F", "G", "H", "J", "K", "L"};
    private String[] thirdRow = {"Shift", "Z", "X", "C", "V", "B", "N", "M"};
    private String[] fourthRow = {"draft", "SPACE", "send"};

    //all keys without shift key press
    String noShift="`1234567890-=qwertyuiop[]\\asdfghjkl;'zxcvbnm,./";
    //special charactors on keyboard that has to be addressed duing keypress
    String specialChars ="~-+[]\\;',.?";

    //TODO: Build CardLayout

    private IController theController;
    private IModel theModel;

    /*
     * constructor to create frame
     */
    public QwertyKeyboard(IController controller)
    {
        this.theController = controller;

        //init and paint frame
        initWidgets();
    }

    /**
     * Method to initialize frame component
     */
    private void initWidgets()
    {
        /*set the layout and place compomnet in place and pack it */
        setLayout(new BorderLayout());
        /*Various panel for the layout */
        JPanel jpKeyboard = new JPanel();
        add(jpKeyboard, BorderLayout.SOUTH);

        //add(text,BorderLayout.WEST);
        // add(scrollPane,BorderLayout.CENTER);

        //layout for keyboard
        jpKeyboard.setLayout(new GridLayout(4,1));
        //pack the components
        //pack();

        /*paint first keyboard row  and add it to the keyboard*/
        //JButtons corresponding to each individual rows
        JButton[] first = new JButton[firstRow.length];
        //get the panel for the  row
        JPanel p = new JPanel(new GridLayout(1, firstRow.length));
        for(int i = 0; i < firstRow.length; ++i)
        {
            JButton b= new JButton(firstRow[i]);
            b.setPreferredSize(new Dimension(100,50));
            first[i] = b;
            p.add(first[i]);
        }
        jpKeyboard.add(p);

        /*paint second keyboard row  and add it to the keyboard*/
        JButton[] second = new JButton[secondRow.length];
        //get the panel for the  row
        p = new JPanel(new GridLayout(1, secondRow.length));
        for(int i = 0; i < secondRow.length; ++i)
        {
            second[i] = new JButton(secondRow[i]);
            p.add(second[i]);

        }
        jpKeyboard.add(p);

        /*paint third keyboard row  and add it to the keyboard*/

        JButton[] third = new JButton[thirdRow.length];
        //get the panel for the  row
        p = new JPanel(new GridLayout(1, thirdRow.length));
        for(int i = 0; i < thirdRow.length; ++i)
        {
            third[i] = new JButton(thirdRow[i]);
            p.add(third[i]);
        }
        jpKeyboard.add(p);

        /*paint fourth keyboard row  and add it to the keyboard*/
        JButton[] fourth = new JButton[fourthRow.length];
        //get the panel for the  row
        p = new JPanel(new GridLayout(1, fourthRow.length));
        for(int i = 0; i < fourthRow.length; ++i)
        {
            fourth[i] = new JButton(fourthRow[i]);
            p.add(fourth[i]);
            if(i==fourthRow.length-2)
                p.add(new JPanel());

        }
        p.add(new JPanel());
        jpKeyboard.add(p);
/*

        //draw the buttons
        for(int i = 0; i < fifthRow.length; ++i)
        {
            if(i==1) //space bar panel
            {
                JButton b = new JButton(fifthRow[i]);
                b.setPreferredSize(new Dimension(400,10));
                b.setBounds(10, 10, 600, 100);
                fifth[i]=b;
                //add empty panels for layout
                p.add(new JPanel());p.add(new JPanel());p.add(new JPanel());p.add(new JPanel());p.add(new JPanel());p.add(new JPanel());p.add(new JPanel());p.add(new JPanel());
            }
            else
            {
                fifth[i]=new JButton(fifthRow[i]);
            }
            if(i==0) //first black panel
            {
                //place a black panel at first
                JPanel  spacePanel = new JPanel();
                p.add(spacePanel);
            }

            p.add(fifth[i]);

        }
        jpKeyboard.add(p);*/
        /*add listeners to all the button */
        for(int i = 0; i < first.length; i++){
            first[i].addActionListener(new ButtonClickListener(theController, firstRow[i]));
        }
        for(int i = 0; i < second.length; i++){
            second[i].addActionListener(new ButtonClickListener(theController, secondRow[i]));
        }
        for(int i = 0; i < third.length; i++){
            third[i].addActionListener(new ButtonClickListener(theController, thirdRow[i]));
        }
        for(int i = 0; i < fourth.length; i++){
            fourth[i].addActionListener(new ButtonClickListener(theController, fourthRow[i]));
        }

    } //end of initWidgets
}
