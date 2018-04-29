package messagechain.view.tabs;

import burp.BurpExtender;
import messagechain.model.Context;
import messagechain.view.UIUtils;
import messagechain.view.abstracts.AbstractTab;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by User on 4/26/2018.
 */
public class TabFilter extends AbstractTab {
    private final Context context;
    private JSplitPane pane;
    private RTextScrollPane codeEditor;
    private RSyntaxTextArea textArea;
    private JPanel panelFilterList;
    private DefaultListModel modelFilters;
    private JList jlistFilters;

    public TabFilter(Context context) {
        this.context = context;
        initUI();
    }

    protected void initUI() {
        pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        pane.setDividerLocation(0.2);
        pane.setRightComponent(getCodeEditor());
        pane.setLeftComponent(getFilterList());
        setLayout(new BorderLayout());
        add(pane,BorderLayout.CENTER);
    }

    public RTextScrollPane getCodeEditor() {
        if (codeEditor == null) {
            textArea = new RSyntaxTextArea(20, 60);
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
            textArea.setCodeFoldingEnabled(true);
            textArea.setEditable(true);
            codeEditor = new RTextScrollPane(textArea);
        }
        return codeEditor;
    }

    public JPanel getFilterList() {
        if(panelFilterList == null){
            panelFilterList = new JPanel(new BorderLayout());
            modelFilters = new DefaultListModel<>();
            jlistFilters = new JList(modelFilters);
            jlistFilters.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jlistFilters.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {

//                    if (!validateUpdate()) {
//                        jlistFilters.setSelectedValue(activeSequence, true);
//                    }
//                    updateSequenceDetail();
//                    activeSequence = jlistFilters.getSelectedValue();
//                    showSequenceDetail();

                }
            });
            JPanel btns = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JButton addSequence = new JButton("+");
            addSequence.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String[] langs = new String[]{"Javascript", "Python", "Ruby"};
                    String[] methods = new String[]{"OnStart","OnRequestPreSet","OnRequestPostSet",
                                                "OnResponse","OnFinish"};

                    JPopupMenu popupMenu = new JPopupMenu();
                    for(String s1: langs) {
                        JMenu submenu = new JMenu(s1);
                        for(String s2: methods){
                            JMenuItem jmi = new JMenuItem(s2);
                            jmi.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    addNewFilter(s1,s2);
                                }
                            });
                            submenu.add(jmi);
                        }
                        popupMenu.add(submenu);
                    }
                    popupMenu.show((Component)e.getSource(), 0, ((Component) e.getSource()).getHeight());
                }
            });
            JButton removeSequence = new JButton("-");
            removeSequence.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    UIUtils.deleteSelectedFromList(jlistFilters,modelFilters);
                }
            });
            btns.add(addSequence);
            btns.add(removeSequence);
            panelFilterList.add(btns,BorderLayout.NORTH);
            panelFilterList.add(new JScrollPane(jlistFilters),BorderLayout.CENTER);
        }
        return panelFilterList;
    }

    private void addNewFilter(String lang, String method) {
    }

    @Override
    public String getTabTitle() {
        return "Filters";
    }
}
