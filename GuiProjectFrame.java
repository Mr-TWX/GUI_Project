/*
* NAME:	Tommaso Marenzi
* OOAssessment: GuiProjectTM
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class GuiProjectTMFrame extends JFrame
{
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGTH = 250;
	private static final GridBagLayout bagLayout = new GridBagLayout();
	private static final GridBagConstraints c = new GridBagConstraints();

	private static JLabel	background = new JLabel (new ImageIcon("ItalialogoTM.png")), //creates a label with a picture as background
						titleLabel = new JLabel("ITALIAN NATIONAL FOOTBALL TEAM MEMBERS"), // creates the label with the title
						instructionLabel = new JLabel("Click NEXT to show players info, click EXIT to close the program."),
						numberLabel = new JLabel("Number:"),
						numberDisplay = new JLabel("**"),
						positionLabel = new JLabel("Position:"),
						positionDisplay = new JLabel("**"),
						nameLabel = new JLabel("Full name:"),
       					nameDisplay = new JLabel("******* *******"),
       					dateOfBirthLabel = new JLabel("Date of birth:"),
        				dateOfBirthDisplay = new JLabel("DD-MM-YYYY");
	private static JButton nextButton = new JButton("NEXT"),
							exitButton = new JButton("EXIT");
	private static int numClicks = 0;


	public GuiProjectTMFrame4()
	{
		super("GuiProjectTM A");
		createComponents();
		setSize(FRAME_WIDTH,FRAME_HEIGTH);
	}

	private void createComponents()//specifies attributes for the components and add them to the panel
	{
		Font labelFont = new Font("Calibri",Font.BOLD,15);//font for labels
		Font textFont = new Font("Calibri", Font.ITALIC,15);//font for text displayed
		Font titleFont = new Font("Calibri", Font.BOLD,20);//font for the title
		setLayout(new BorderLayout());
		add(background);//add background to the panel

		background.setLayout(bagLayout); //set background layout

		//set title label
		titleLabel.setFont(titleFont);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_START;
		c.weightx = 0.5;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		background.add(titleLabel, c);

		//set instructions Label
		instructionLabel.setFont(textFont);
		c.fill =GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_START;
		c.weightx = 0.5;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 1;
		background.add(instructionLabel, c);

		//set container with number labels
		numberLabel.setFont(labelFont);
		numberDisplay.setFont(textFont);
		c.fill =GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 2;
		background.add(numberLabel, c);
		c.fill =GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 2;
		background.add(numberDisplay, c);

		//set position labels
		positionLabel.setFont(labelFont);
		positionDisplay.setFont(textFont);
		c.fill =GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 3;
		background.add(positionLabel, c);
		c.fill =GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 3;
		background.add(positionDisplay, c);

        //set name labels
        nameLabel.setFont(labelFont);
		nameDisplay.setFont(textFont);
		c.fill =GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 4;
		background.add(nameLabel, c);
		c.fill =GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 4;
		background.add(nameDisplay, c);

        //set date of birth labels
        dateOfBirthLabel.setFont(labelFont);
		dateOfBirthDisplay.setFont(textFont);
		c.fill =GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 5;
		background.add(dateOfBirthLabel, c);
		c.fill =GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 5;
		background.add(dateOfBirthDisplay, c);

		//set next button and exit button
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_END;
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 6;
		background.add(nextButton, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_END;
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 6;
		background.add(exitButton, c);

		//add actionListener for nextButton
		nextButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				fileReader();
				numClicks++;
			}
		});

		//add actionListener for exitButton
		exitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				setVisible(false);
			}
		});

	}//end createComponents()

	// method which performs the reading of the file
	private static void fileReader()
	{
		try
		{
			//construct the scanner for reading
			File inputFile = new File("ItalianNationalFootballTeamTM.txt");
			Scanner in = new Scanner(inputFile);


			//read the input
			while(in.hasNextLine())
			{
				String line = in.nextLine();//read the next line
				String element = "";
				int counter = 0; //variable to count the number of tab found
				int j = 0;// variable to keep track of the position of the previous tab

				//read a nuber of line equals to the number of clicks
				for(int x=0; x<numClicks; x++)
				{
					line = in.nextLine();
				}

				//read through the line until a tab is found
				for(int i=0;counter < 4 && i < line.length(); i++)
				{
					char ch = line.charAt(i); // convert the string element into a char type

					if(ch == '\t')
					{

						switch (counter)
						{
							case 0:	element = line.substring(j, i+1);
									numberDisplay.setText(element);
									break;
							case 1:	element = line.substring(j, i+1);
									positionDisplay.setText(element);
									break;
							case 2:	element = line.substring(j, i+1);
									nameDisplay.setText(element);
									break;
							case 3:	element = line.substring(j, i+1);
									dateOfBirthDisplay.setText(element);
									break;
							default:	numberDisplay.setText("END OF SWITCH");
										break;
						}//end switch
						counter++; //increment the counter for number of tab found
						j = i; // set j to the position of i
					}//end if
				}//end for
				in.close();
			}//end while
		}//end try
		catch(Exception e){}
	}//end fileReader()
}//end GuiProjectTMFrame
