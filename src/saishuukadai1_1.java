import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
public class saishuukadai1_1 extends JFrame implements ListSelectionListener {

	private JLabel label01;
	private JLabel label02;
	private JPanel cardPanel;
	private CardLayout layout;
	private JTextField field;
	private JTextField field1;
	private JTextField field2;
	private JTextField field3;
	private DefaultListModel<MyData> model01;
	private DefaultListModel<MyData> model02;
	private JList<MyData> list01;
	private JList<MyData> list02;

	@Override
	public void valueChanged(ListSelectionEvent e) {
		Object[] arr = list01.getSelectedValues();
		String result = "";
		for (Object obj : arr) {
			MyData data = (MyData) obj;
			result += data.getItems() + " ";
		}
		label01.setText(result);
	}
	

	class MyActionListener1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			layout.show(cardPanel, cmd);
		}
	}

	class MyActionListener2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			layout.show(cardPanel, cmd);
		}
	}

	class RegButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String name = field.getText();
			int tempint = Integer.parseInt(field1.getText());
			String items =field2.getText();
			model02.addElement(new MyData(name, tempint, items));
		}
	}
	
	class RentalButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int index = list02.getSelectedIndex();
			if (index != -1) {
				
			} else {
				JOptionPane.showMessageDialog(list02, "None selected!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	class ModifiButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int index = list02.getSelectedIndex();
			if (index != -1) {
				
			} else {
				JOptionPane.showMessageDialog(list02, "None selected!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	class DelButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int index = list02.getSelectedIndex();
			if (index != -1) {
				model02.remove(index);
			} else {
				JOptionPane.showMessageDialog(list02, "None selected!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	class QuitButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	class QuitButtonAction1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	class MyData {
		private String name;
		private int c_number;
		private String items;

		public MyData(String s, int n, String i) {
			this.name = s;
			this.c_number = n;
			this.items = i;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getnumber() {
			return c_number;
		}

		public void setnumber(int age) {
			this.c_number = age;
		}

		public String getItems() {
			return items;
		}

		public void setItems(String items) {
			this.items = items;
		}

		@Override
		public String toString() {
			return "Name：" + this.name + " No:" + this.c_number + " items：" + this.items;
		}
	}

	public Component createComponents(){

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		model02 = new DefaultListModel<MyData>();
		model02.addElement(new MyData("Tarou Yamada", 1, "table chest"));
		model02.addElement(new MyData("Hanako Tanaka", 2, "table"));
		model02.addElement(new MyData("Takuya Kobayashi", 3, "chest"));
		list02 = new JList<MyData>(model02);
		this.add(list02, BorderLayout.CENTER);
		list02.addListSelectionListener(this);
		label02 = new JLabel("This is Sample.");
		label02.setFont(new Font("Serif", Font.BOLD, 24));
		this.add(label02, BorderLayout.NORTH);
		this.setSize(new Dimension(300, 200));
		
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		model01 = new DefaultListModel<MyData>();
		model01.addElement(new MyData("Tarou Yamada", 1, "table chest"));
		model01.addElement(new MyData("Hanako Tanaka", 2, "table"));
		model01.addElement(new MyData("Takuya Kobayashi", 3, "chest"));
		list01 = new JList<MyData>(model01);
		this.add(list01, BorderLayout.CENTER);
		list01.addListSelectionListener(this);
		label01 = new JLabel("This is Sample.");
		label01.setFont(new Font("Serif", Font.BOLD, 24));
		this.add(label01, BorderLayout.NORTH);
		this.setSize(new Dimension(300, 200));

		
		
		JPanel panel01 = new JPanel();
		JPanel panel02 = new JPanel();
		
		cardPanel = new JPanel();
		layout = new CardLayout();
		cardPanel.setLayout(layout);
		
		cardPanel.add(panel01, "customer");
		cardPanel.add(panel02, "staff");

		JButton firstButton = new JButton("customer");
		firstButton.addActionListener(new MyActionListener1());
		firstButton.setActionCommand("customer");

		JButton secondButton = new JButton("staff");
		secondButton.addActionListener(new MyActionListener1());
		secondButton.setActionCommand("staff");
		
		JPanel btnPanel = new JPanel();
		btnPanel.add(firstButton);
		btnPanel.add(secondButton);
		
		Container contentPane = getContentPane();
		contentPane.add(cardPanel, BorderLayout.CENTER);
		contentPane.add(btnPanel, BorderLayout.PAGE_END);
		
		
		
		field = new JTextField("Please input Name");
		field1 = new JTextField("Please input Nomber");
		field2 = new JTextField("Please input items");
		
		JScrollPane scrollPane02 = new JScrollPane(list02);
		scrollPane02.createVerticalScrollBar();
		scrollPane02.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JButton regButton = new JButton("Register");
		RegButtonAction regButtonListener = new RegButtonAction();
		regButton.addActionListener( regButtonListener );
		regButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton delButton = new JButton("Delete");
		DelButtonAction delButtonListener = new DelButtonAction();
		delButton.addActionListener( delButtonListener );

		JButton quitButton = new JButton("Quit");
		QuitButtonAction quitButtonListener = new QuitButtonAction();
		quitButton.addActionListener( quitButtonListener );
		
		
		JPanel subPane02 = new JPanel();
		subPane02.setLayout(new GridLayout(1, 0));
		subPane02.add(delButton);
		subPane02.add(Box.createRigidArea(new Dimension(30, 10)));
		subPane02.add(quitButton);

		JPanel mainPane02 = new JPanel();
		mainPane02.setBorder(BorderFactory.createEmptyBorder( 30, 30, 30, 30 ));
		mainPane02.setLayout(new BoxLayout(mainPane02, BoxLayout.Y_AXIS));
		mainPane02.add(field);
		mainPane02.add(field1);
		mainPane02.add(field2);

		mainPane02.add(Box.createRigidArea(new Dimension(10, 20)));
		mainPane02.add(regButton);
		mainPane02.add(Box.createRigidArea(new Dimension(10, 30)));
		mainPane02.add(scrollPane02);
		mainPane02.add(Box.createRigidArea(new Dimension(10, 20)));
		mainPane02.add(subPane02);
		
		
		
		
		JScrollPane scrollPane01 = new JScrollPane(list01);
		scrollPane01.createVerticalScrollBar();
		scrollPane01.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JButton quitButton1 = new JButton("Quit");
		QuitButtonAction1 quitButtonListener1 = new QuitButtonAction1();
		quitButton1.addActionListener( quitButtonListener1 );
		
		
		JPanel subPane01 = new JPanel();
		subPane01.setLayout(new GridLayout(1, 0));
		subPane01.add(Box.createRigidArea(new Dimension(30, 10)));
		subPane01.add(quitButton1);
		JPanel mainePane01 = new JPanel();
		mainePane01.setBorder(BorderFactory.createEmptyBorder( 30, 30, 30, 30 ));
		mainePane01.setLayout(new BoxLayout(mainePane01, BoxLayout.Y_AXIS));
		mainePane01.add(Box.createRigidArea(new Dimension(10, 20)));
		mainePane01.add(Box.createRigidArea(new Dimension(10, 30)));
		mainePane01.add(scrollPane01);
		mainePane01.add(Box.createRigidArea(new Dimension(10, 20)));
		mainePane01.add(subPane01);
		
		
		panel01.add(mainePane01);
		panel01.add(subPane01);
		panel02.add(mainPane02);
		panel02.add(subPane02);
		return contentPane;
		
		

	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("gamen_test");
		frame.setSize(900, 600);
		frame.setLocationRelativeTo(null);
		saishuukadai1_1 app = new saishuukadai1_1();
		Component contents = app.createComponents();
		frame.getContentPane().add(contents, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	
	}

}