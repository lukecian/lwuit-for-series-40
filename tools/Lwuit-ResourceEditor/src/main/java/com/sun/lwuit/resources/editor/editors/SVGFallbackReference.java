/*
 * Copyright (c) 2008, 2010, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores
 * CA 94065 USA or visit www.oracle.com if you need additional information or
 * have any questions.
 */

package com.sun.lwuit.resources.editor.editors;

import com.sun.lwuit.resources.editor.ResourceEditorView;
import java.util.prefs.Preferences;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Shai Almog
 */
public class SVGFallbackReference extends javax.swing.JPanel {

    /** Creates new form SVGFallbackReference */
    public SVGFallbackReference() {
        initComponents();
        int w = getSVGWidth();
        int h = getSVGHeight();
        width.setModel(new SpinnerNumberModel(w, 50, 1024, 10));
        height.setModel(new SpinnerNumberModel(h, 50, 1024, 10));
    }

    public static int getSVGWidth() {
        return Preferences.userNodeForPackage(ResourceEditorView.class).getInt("svgW", 240);
    }

    public static int getSVGHeight() {
        return Preferences.userNodeForPackage(ResourceEditorView.class).getInt("svgH", 320);
    }

    public void completeSVG() {
        getSelectedWidth();
        getSelectedHeight();
    }

    public int getSelectedHeight() {
        int h = ((Number)height.getValue()).intValue();
        Preferences.userNodeForPackage(ResourceEditorView.class).putInt("svgH", h);
        return h;
    }

    public int getSelectedWidth() {
        int w = ((Number)width.getValue()).intValue();
        Preferences.userNodeForPackage(ResourceEditorView.class).putInt("svgW", w);
        return w;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        width = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        height = new javax.swing.JSpinner();

        setName("Form"); // NOI18N

        jLabel1.setText("Width");
        jLabel1.setName("jLabel1"); // NOI18N

        width.setName("width"); // NOI18N

        jLabel2.setText("Height");
        jLabel2.setName("jLabel2"); // NOI18N

        height.setName("height"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(width)
                    .addComponent(height))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(width, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(height, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner height;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSpinner width;
    // End of variables declaration//GEN-END:variables

}
