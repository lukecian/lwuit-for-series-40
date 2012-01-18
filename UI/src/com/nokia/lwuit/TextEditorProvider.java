/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nokia.lwuit;

/**
 * This class hides away the actual Nokia texteditor implementation. Making it possible to run lwuit in
 * devices that doesn't support Nokia UI API. The methods are mirrored from the actual TextEditor class so
 * check that for definition of each method.
 * @author tkor
 */
public abstract class TextEditorProvider {
    
    public abstract void delete(int offset, int length);
    public abstract int getBackgroundColor();
    public abstract int getCaretPosition();
    public abstract int getConstraints();
    public abstract String getContent();
    public abstract int getContentHeight();
    public abstract javax.microedition.lcdui.Font getFont();
    public abstract int getForegroundColor();
    public abstract String getInitialInputMode();
    public abstract int getLineMarginHeight();
    public abstract int getMaxSize();
    public abstract String getSelection();
    public abstract int getVisibleContentPosition();
    public abstract int getZPosition();
    public abstract boolean hasFocus();
    public abstract void insert(java.lang.String text, int position);
    public abstract boolean isMultiline();
    public abstract void setBackgroundColor(int color);
    public abstract void setCaret(int index);
    public abstract void setConstraints(int constraints);
    public abstract void setContent(java.lang.String content);
    public abstract void setFocus(boolean focused);
    public abstract void setFont(javax.microedition.lcdui.Font font);
    public abstract void setForegroundColor(int color);
    public abstract void setHighlightBackgroundColor(int color);
    public abstract void setHighlightForegroundColor(int color);
    public abstract void setInitialInputMode(java.lang.String characterSubset);
    public abstract int setMaxSize(int maxSize);
    public abstract void setMultiline(boolean aMultiline);
    public abstract void setParent(java.lang.Object parent);
    public abstract void setPosition(int x, int y);
    public abstract void setSelection(int index, int length);
    public abstract void setSize(int width, int height);
    public abstract void setTextEditorListener(TextEditorListener listener);
    public abstract void setVisible(boolean visible);
    public abstract void setZPosition(int z);
    public abstract int size();
    
    
    public static interface TextEditorListener {
        public void inputAction(TextEditorProvider textEditor, int actions);
    }
    
}