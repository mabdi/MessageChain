package messagechain.view.tabs;

import burp.BurpExtender;
import burp.IBurpExtenderCallbacks;
import burp.ITextEditor;
import messagechain.model.Flow_Request;
import messagechain.model.adapters.TableModelResponseOut;
import messagechain.view.abstracts.AbstractTab;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by User on 4/26/2018.
 */
public class TabMessages extends AbstractTab {

    private JPanel panelRequests;

    @Override
    public String getTabTitle() {
        return "Messages";
    }

    private JSplitPane splitPane;
    private JList<Flow_Request> listRequests;
    private JSplitPane verticalSplitPane;
    private DefaultListModel<Flow_Request> modelRequests;
    private JTabbedPane tabs;
    private ITextEditor requestViewer;
    private ITextEditor responseViewer;
    private JPanel responseOutputPanel;
    private JTable jtableResponseOut;
    private TableModelResponseOut modelResponseOut;
    private Flow_Request currentRequest;
    private String strParams;

    @Override
    protected void initUI() {
        setLayout(new BorderLayout());
        add(getSplitPane(), BorderLayout.CENTER);
    }

    public JSplitPane getSplitPane() {
        if (splitPane == null) {
            splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
            splitPane.setLeftComponent(new JScrollPane(getRequestList()));
            splitPane.setRightComponent(getRequestTabs());
            splitPane.setDividerLocation(0.3);
        }
        return splitPane;
    }

    public JPanel getRequestList() {
        if (panelRequests == null) {
            panelRequests = new JPanel(new BorderLayout());
            modelRequests = new DefaultListModel<>();
            listRequests = new JList<>(modelRequests);
            listRequests.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listRequests.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (e.getValueIsAdjusting()) {
                        updateRequestDetail();
                        currentRequest = listRequests.getSelectedValue();
                        showRequestDetail();
                    }
                }
            });

            JPanel btns = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JButton up = new JButton("Up");
            JButton down = new JButton("Down");
            JButton delete = new JButton("Delete");
            btns.add(up);
            btns.add(down);
            btns.add(delete);

            panelRequests.add(listRequests,BorderLayout.CENTER);
            panelRequests.add(btns,BorderLayout.NORTH);
        }
        return panelRequests;
    }


    public JSplitPane getVerticalSplitPane() {
        if (verticalSplitPane == null) {
            verticalSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
            IBurpExtenderCallbacks callbacks = BurpExtender.getInstance().getCallbacks();
            responseViewer = callbacks.createTextEditor();
            verticalSplitPane.setTopComponent(responseViewer.getComponent());
            verticalSplitPane.setBottomComponent(getResponseOutputPanel());
            verticalSplitPane.setResizeWeight(0.5);
        }
        return verticalSplitPane;
    }


    public void updateRequestDetail() {
        if (currentRequest == null) return;
        currentRequest.setModifiedRequest(requestViewer.getText());
    }

    private void showRequestDetail() {
//        if (currentRequest.getModifiedRequest() == null) {
//            currentRequest.setModifiedRequest(currentRequest.getRequest().getRequest());
//        }
//        requestViewer.setText(currentRequest.getModifiedRequest());
//        responseViewer.setText(currentRequest.getRequest().getResponse());
//        modelResponseOut.changeData(currentRequest.getOutputParams());
//        getRequestTabs().setSelectedIndex(0);
    }

    public JTabbedPane getRequestTabs() {
        if (tabs == null) {
            tabs = new JTabbedPane();
            IBurpExtenderCallbacks callbacks = BurpExtender.getInstance().getCallbacks();
            requestViewer = callbacks.createTextEditor();
            JPanel reqPanel = new JPanel(new BorderLayout());
            JPanel reqToolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JButton btnReqParam = new JButton("Add param var");
            btnReqParam.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    JPopupMenu popup = new JPopupMenu();
//                    JMenuItem jmi = new JMenuItem("var");
//                    jmi.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            addPlaceHolder(requestViewer, Settings.PARAM_IDENTIFIER);
//                        }
//                    });
//                    popup.add(jmi);
//                    String[] params = strParams.split(",");
//                    for (String p:params) {
//                        final String data = Settings.PARAM_IDENTIFIER.replace("var", p.trim());
//                        jmi = new JMenuItem(p.trim());
//                        jmi.addActionListener(new ActionListener() {
//                            @Override
//                            public void actionPerformed(ActionEvent e) {
//                                addPlaceHolder(requestViewer, data);
//                            }
//                        });
//                        popup.add(jmi);
//                    }
//                    popup.show((Component)e.getSource(), 0, ((Component) e.getSource()).getHeight());
                }
            });
            JButton btnReqLocal = new JButton("Add local var");
            btnReqLocal.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    JPopupMenu popup = new JPopupMenu();
//                    JMenuItem jmi = new JMenuItem("var");
//                    jmi.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            addPlaceHolder(requestViewer, Settings.LOCAL_IDENTIFIER);
//                        }
//                    });
//                    popup.add(jmi);
//                    Enumeration<Flow_Request> els = modelRequests.elements();
//                    while (els.hasMoreElements()) {
//                        Flow_Request rq = els.nextElement();
//                        for(ResponseOut out: rq.getOutputParams()) {
//                            final String data = Settings.LOCAL_IDENTIFIER.replace("var", out.getName());
//                            jmi = new JMenuItem(out.getName());
//                            jmi.addActionListener(new ActionListener() {
//                                @Override
//                                public void actionPerformed(ActionEvent e) {
//                                    addPlaceHolder(requestViewer, data);
//                                }
//                            });
//                            popup.add(jmi);
//                        }
//                    }
//                    popup.show((Component)e.getSource(), 0, ((Component) e.getSource()).getHeight());
                }
            });
            JButton btnReqGlobal = new JButton("Add Global var");
            btnReqGlobal.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    addPlaceHolder(requestViewer, Settings.GLOBAL_IDENTIFIER);
                }
            });
            reqToolbar.add(btnReqParam);
            reqToolbar.add(btnReqLocal);
            reqToolbar.add(btnReqGlobal);

            reqPanel.add(requestViewer.getComponent(), BorderLayout.CENTER);
            reqPanel.add(reqToolbar, BorderLayout.NORTH);

            tabs.addTab("Request", reqPanel);
            tabs.addTab("Response", getVerticalSplitPane());
        }
        return tabs;
    }

    private void addPlaceHolder(ITextEditor editor, String placeHoder) {
        if (editor.getSelectedText() != null && editor.getSelectedText().length > 0) {
//            int[] bnd = editor.getSelectionBounds();
//            byte[] text = editor.getText();
//            byte[] newText = new byte[bnd[0] + placeHoder.length() + (text.length - bnd[1])];
//            System.arraycopy(text, 0, newText, 0, bnd[0]);
//            System.arraycopy(placeHoder.getBytes(), 0, newText, bnd[0], placeHoder.getBytes().length);
//            System.arraycopy(text, bnd[1], newText, bnd[0] + placeHoder.getBytes().length, text.length - bnd[1] );
//            editor.setText(newText);
//            editor.setSearchExpression(Settings.SECTION_CHAR);
        }
    }


    public JPanel getResponseOutputPanel() {
        if (responseOutputPanel == null) {
            responseOutputPanel = new JPanel(new BorderLayout());
            JPanel btns = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JButton addSequence = new JButton("+");
            addSequence.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    if (listRequests.getSelectedIndex() < 0) {
//                        return;
//                    }
//
//                    JPopupMenu popup = new JPopupMenu();
//                    Map<Integer, String> kvp = ResponseOut.getTypesString();
//                    for (String v:kvp.values() ) {
//                        JMenuItem jmi = new JMenuItem(v);
//                        final String data = v;
//                        jmi.addActionListener(new ActionListener() {
//                            @Override
//                            public void actionPerformed(ActionEvent e) {
//                                DialogResponseOutput dlg = new DialogResponseOutput();
//                                ResponseOut responseOut = dlg.getData( data );
//                                if (responseOut != null) {
//                                    listRequests.getSelectedValue().addOutputParam(responseOut);
//                                    modelResponseOut.fireTableDataChanged();
//                                }
//                            }
//                        });
//                        popup.add(jmi);
//                    }
//                    // show on the button?
//                    popup.show((Component)e.getSource(), 0, ((Component) e.getSource()).getHeight());
                }
            });
            JButton edit = new JButton("Edit");
            edit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    if (getJtableResponseOut().getSelectedRow() < 0) {
//                        return;
//                    }
//                    DialogResponseOutput dlg = new DialogResponseOutput();
//                    dlg.getEditData(modelResponseOut.getItem(getJtableResponseOut().getSelectedRow()));
                }
            });
            JButton removeSequence = new JButton("-");
            removeSequence.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    if (getJtableResponseOut().getSelectedRow() < 0) {
//                        return;
//                    }
//                    listRequests.getSelectedValue().getOutputParams().remove(getJtableResponseOut().getSelectedRow());
//                    modelResponseOut.fireTableDataChanged();
                }
            });
            btns.add(addSequence);
            btns.add(edit);
            btns.add(removeSequence);
            responseOutputPanel.add(btns, BorderLayout.NORTH);
            responseOutputPanel.add(new JScrollPane(getJtableResponseOut()), BorderLayout.CENTER);
        }
        return responseOutputPanel;
    }

    public JTable getJtableResponseOut() {
        if (jtableResponseOut == null) {
            modelResponseOut = new TableModelResponseOut();
            jtableResponseOut = new JTable();
            jtableResponseOut.setModel(modelResponseOut);
        }
        return jtableResponseOut;
    }


//    public void setData(Flow_Sequence sequence,String params) {
//        this.strParams = params;
//        for (Flow_Request obj : sequence.getRequests()) {
//            modelRequests.addElement(obj);
//        }
//    }
}
