/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nokia.lwuit;

import com.nokia.mid.ui.gestures.GestureEvent;
import com.sun.lwuit.Display;
import com.sun.lwuit.Form;
import com.sun.lwuit.impl.LWUITImplementation;
import com.sun.lwuit.impl.s40.S40GestureImplementation;
import java.lang.ref.WeakReference;

/**
 * A abstract class to handle Gestures. Extend from this class and implement the
 * gestureAction-method. Using one of the two static methods, you can activate the
 * handler to listen all gestures or listen only when spesific form is visible.
 * @author tkor
 */
public abstract class GestureHandler {
    
    public static final int GESTURE_FLICK = 16;
    public static final int GESTURE_LONG_PRESS = 2;
    public static final int GESTURE_LONG_PRESS_REPEATED = 32;
    public static final int GESTURE_TAP = 1;
    public static final int GESTURE_DRAG = 4;
    public static final int GESTURE_DROP = 8;
    public static final int GESTURE_ALL = 49279;
    public static final int GESTURE_PINCH = 64;
    
    private WeakReference form;
    
    private int gestures;
    
    public GestureHandler() {
        gestures = GESTURE_ALL;
    }
    public GestureHandler(int gestures) {
        this.gestures = gestures;
    }
    
    public int getGestures() {
        return this.gestures;
    }
    
    /**
    * Set global gesturehandler that will receive all the gestures. Setting it null will 
    * remove current global gesturehandler.
    * @param h Class that implements the GestureHandler interface
    */
    public static void setGlobalGestureHandler(GestureHandler h) {
        LWUITImplementation impl = Display.getInstance().getImplementation();
        if(impl instanceof S40GestureImplementation) {
            ((S40GestureImplementation)impl).setGlobalGestureHandler(h);
        }
    }
    /**
    * Set gesturehandler for a spesific form. The handler will receive gesture-
    * events when the form is visible.
    * @param f
    * @param h 
    */
    public static void setFormGestureHandler(Form f, GestureHandler h) { 
        h.setForm(f);
        LWUITImplementation impl = Display.getInstance().getImplementation();
        if(impl instanceof S40GestureImplementation) {
            ((S40GestureImplementation) impl).addGestureHandler(h);
        }
    }
    /**
     * Set the form of which gestures are listened
     * @param f 
     */
    private void setForm(Form f) {
        form = new WeakReference(f);
    }
    /**
    * Return the LWUIT Form associated with this handler
    * @return LWUIT Form
    */
    public Form getForm() {
        return (Form) form.get();
    }
    /**
    * Override to handle gestureEvents.
    * @param e GestureEvent that will contain information about the current gesture.
    */
    public abstract void gestureAction(GestureEvent e);
}
