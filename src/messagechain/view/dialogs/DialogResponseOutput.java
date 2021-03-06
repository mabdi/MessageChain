package messagechain.view.dialogs;

import messagechain.model.ResponseOut;
import messagechain.model.Settings;
import messagechain.view.UIUtils;
import messagechain.view.abstracts.AbstractDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;

/**
 * Created by admin on 08/08/2017.
 */
public class DialogResponseOutput extends AbstractDialog {
    private JTextField txtPlaceHolder;
    private JComboBox<String> comboType;
    private JTextField txtValues;
    private JPanel toolbar;
    private JButton btnAdd;
    private JButton btncancel;
    private JCheckBox checkBoxGlobal;
    private ResponseOut responseOut;
    private ResponseOut result;
//    private JButton btnVarName;

    public DialogResponseOutput() {
        super(false);
    }

    @Override
    protected void initUI() {
        setSize(450,350);
        setTitle("Make Variable");
        setLocationRelativeTo(getParentWindow());
        installEscapeCloseOperation();
        SpringLayout layoutTop = new SpringLayout();
        JPanel topPanel = new JPanel(layoutTop);

        JLabel lblhint = new JLabel("Use the var name `return` and check `Global` option, to set return value.");

        JLabel lblSeqName = new JLabel("Var Name: ");
        txtPlaceHolder = new JTextField("",18);
        txtPlaceHolder.setComponentPopupMenu(UIUtils.buildNewPopMenuCopyCutPaste());
//        btnVarName = new JButton("Variable Name");
//        btnVarName.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JPopupMenu popup = new JPopupMenu();
//                JMenuItem jmi = new JMenuItem(Settings.LOCAL_IDENTIFIER.replace("var","cookie"));
//                jmi.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        txtPlaceHolder.setText(Settings.LOCAL_IDENTIFIER.replace("var","cookie"));
//                    }
//                });
//                popup.add(jmi);
//                JMenuItem jmi2 = new JMenuItem(Settings.GLOBAL_IDENTIFIER.replace("var","cookie"));
//                jmi2.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        txtPlaceHolder.setText(Settings.GLOBAL_IDENTIFIER.replace("var","cookie"));
//                    }
//                });
//                popup.add(jmi2);
//                JMenuItem jmi3 = new JMenuItem(Settings.PARAM_IDENTIFIER.replace("var","cookie"));
//                jmi3.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        txtPlaceHolder.setText(Settings.PARAM_IDENTIFIER.replace("var","cookie"));
//                    }
//                });
//                popup.add(jmi3);
//
//                popup.show((Component)e.getSource(), 0, ((Component) e.getSource()).getHeight());
//            }
//        });

        JLabel lblBase = new JLabel("Type: ");
        comboType = new JComboBox<>();
        DefaultComboBoxModel<String> modelCombo = new DefaultComboBoxModel<>();
        Map<Integer, String> kvp = ResponseOut.getTypesString();
        for (String v:kvp.values() ) {
            modelCombo.addElement(v);
        }
        comboType.setModel(modelCombo);
        checkBoxGlobal = new JCheckBox("Global");

        comboType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<Integer, String> kvp = ResponseOut.getTypesString();
                if(comboType.getSelectedItem().equals(kvp.get(ResponseOut.TYPE_CAPTCHA))){
                    txtValues.setEnabled(false);
                }else{
                    txtValues.setEnabled(true);
                }
            }
        });
        JLabel lblCookie = new JLabel("Values: ");
        txtValues = new JTextField("",18 );
        txtValues.setComponentPopupMenu(UIUtils.buildNewPopMenuCopyCutPaste());

        topPanel.add(lblhint);
        topPanel.add(lblSeqName);
        topPanel.add(txtPlaceHolder);
//        topPanel.add(btnVarName);
        topPanel.add(lblBase);
        topPanel.add(comboType);
        topPanel.add(lblCookie);
        topPanel.add(txtValues);
        topPanel.add(checkBoxGlobal);

        int PADD_WEST = 80;
        int PADD_EAST = -20;


        layoutTop.putConstraint(SpringLayout.WEST,lblhint,10,SpringLayout.WEST,topPanel);
        layoutTop.putConstraint(SpringLayout.NORTH,lblhint,10,SpringLayout.NORTH,topPanel);

        layoutTop.putConstraint(SpringLayout.WEST,lblSeqName,10,SpringLayout.WEST,topPanel);
        layoutTop.putConstraint(SpringLayout.NORTH,lblSeqName,10,SpringLayout.SOUTH,lblhint);
//        layoutTop.putConstraint(SpringLayout.NORTH,btnVarName,0,SpringLayout.NORTH,lblSeqName);
//        layoutTop.putConstraint(SpringLayout.EAST,btnVarName,PADD_EAST,SpringLayout.EAST,topPanel);
//        layoutTop.putConstraint(SpringLayout.WEST,btnVarName,-170,SpringLayout.EAST,topPanel);
        layoutTop.putConstraint(SpringLayout.WEST,txtPlaceHolder,PADD_WEST,SpringLayout.WEST,topPanel);
        layoutTop.putConstraint(SpringLayout.EAST,txtPlaceHolder,PADD_EAST,SpringLayout.EAST,topPanel);
        layoutTop.putConstraint(SpringLayout.NORTH,txtPlaceHolder,0,SpringLayout.NORTH,lblSeqName);



        layoutTop.putConstraint(SpringLayout.WEST,lblBase,10,SpringLayout.WEST,topPanel);
        layoutTop.putConstraint(SpringLayout.NORTH,lblBase,10,SpringLayout.SOUTH,lblSeqName);
        layoutTop.putConstraint(SpringLayout.WEST,comboType,PADD_WEST,SpringLayout.WEST,topPanel);
        layoutTop.putConstraint(SpringLayout.EAST,comboType,PADD_EAST,SpringLayout.EAST,topPanel);
        layoutTop.putConstraint(SpringLayout.NORTH,comboType,0,SpringLayout.NORTH,lblBase);

        layoutTop.putConstraint(SpringLayout.WEST,lblCookie,10,SpringLayout.WEST,topPanel);
        layoutTop.putConstraint(SpringLayout.NORTH,lblCookie,10,SpringLayout.SOUTH,lblBase);
        layoutTop.putConstraint(SpringLayout.WEST,txtValues,PADD_WEST,SpringLayout.WEST,topPanel);
        layoutTop.putConstraint(SpringLayout.EAST,txtValues,PADD_EAST,SpringLayout.EAST,topPanel);
        layoutTop.putConstraint(SpringLayout.NORTH,txtValues,0,SpringLayout.NORTH,lblCookie);

        layoutTop.putConstraint(SpringLayout.WEST,checkBoxGlobal,PADD_WEST,SpringLayout.WEST,topPanel);
        layoutTop.putConstraint(SpringLayout.EAST,checkBoxGlobal,PADD_EAST,SpringLayout.EAST,topPanel);
        layoutTop.putConstraint(SpringLayout.NORTH,checkBoxGlobal,10,SpringLayout.SOUTH,txtValues);




        setLayout(new BorderLayout());
        add(topPanel,BorderLayout.CENTER);
        add(getToolbar(),BorderLayout.SOUTH);
    }

    public Component getToolbar() {
        if(toolbar == null){
            toolbar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            btnAdd = new JButton("Add");
            btnAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    responseOut.setType(getSelectedType());
                    responseOut.setName(txtPlaceHolder.getText());
                    responseOut.setParam(txtValues.getText());
                    responseOut.setGlobal(checkBoxGlobal.isSelected());
                    result = responseOut;
                    dissmiss();
                }
            });
            btncancel = new JButton("Close");
            btncancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dissmiss();
                }
            });
            toolbar.add(btnAdd);
            toolbar.add(btncancel);
        }
        return toolbar;
    }

//    public ResponseOut getData(ResponseOut responseOut){
//        this.responseOut = responseOut;
//        initData();
//        setVisible(true);
//        return new ResponseOut(-1,);
//    }

    public ResponseOut getData(String data){
        initData(data);
        responseOut = new ResponseOut(-1,getSelectedType()
                ,txtPlaceHolder.getText(),txtValues.getText(),checkBoxGlobal.isSelected());
        setVisible(true);
        return result;
    }

    public ResponseOut getEditData(ResponseOut responseOut){
        this.responseOut = responseOut;
        initData(responseOut);
        setVisible(true);

        return result;
    }

    private void initData(ResponseOut responseOut) {
        txtValues.setText(responseOut.getParam());
        checkBoxGlobal.setSelected(responseOut.isGlobal());
        txtPlaceHolder.setText(responseOut.getName());
        String selected = ResponseOut.getTypesString().get(responseOut.getType());
        comboType.setSelectedItem(selected);
    }

    private void initData(String data) {
        comboType.setSelectedItem(data);
        if (data.equals(ResponseOut.getTypesString().get(ResponseOut.TYPE_CAPTCHA))) {
            txtPlaceHolder.setText("captcha");
        }
        if (data.equals(ResponseOut.getTypesString().get(ResponseOut.TYPE_COOKIE))) {
            txtPlaceHolder.setText("cookie");
            txtValues.setText(Settings.SESSION_COOKIENAME);
        }
        if (data.equals(ResponseOut.getTypesString().get(ResponseOut.TYPE_HIDDEN))) {
            txtPlaceHolder.setText("form");
        }
        if (data.equals(ResponseOut.getTypesString().get(ResponseOut.TYPE_CSS))) {
            txtPlaceHolder.setText("css");
        }
        if (data.equals(ResponseOut.getTypesString().get(ResponseOut.TYPE_REGEX))) {
            txtPlaceHolder.setText("regex");
        }
        txtPlaceHolder.requestFocus();
    }

    public int getSelectedType() {
        String selected = (String) comboType.getSelectedItem();
        Set<Map.Entry<Integer, String>> kvp = ResponseOut.getTypesString().entrySet();
        for (Map.Entry<Integer,String> kv:kvp) {
            if(kv.getValue().equals(selected)){
                return kv.getKey();
            }
        }
        return ResponseOut.TYPE_REGEX;
    }
}
