package com.nokia.lwuit.components;

import com.sun.lwuit.*;
import com.sun.lwuit.geom.Dimension;
import com.sun.lwuit.plaf.Style;
import com.sun.lwuit.plaf.UIManager;

/**
 * A component that visually displays the progress of an operation. The
 * component can be used in indeterminate or determinate mode.
 * 
 * In indeterminate mode the progress is indicated by spinning animation. It
 * is meant for cases where the progress cannot be reliably measured.
 * 
 * In determinate mode the progress is represented by a progress bar. This
 * mode simply delegates the functionality to Slider component.
 * 
 */
public class ProgressIndicator extends Component {

    private boolean indeterminate;        
    private Image image;
    private Image rotatedImage;
    private int angle;
    private Style indeterminateStyle;
    private Slider progressBar;
    private long timeStamp;    
    private static final int MILLISECONDS_BETWEEN_UPDATES = 66;
    
    /** 
     * Constructs a new indeterminate ProgressIndicator.
     * 
     */
    public ProgressIndicator() {
        this(true);
    }
    
    /** 
     * Constructs a new ProgressIndicator.
     * 
     * @param indeterminate if true the progress indicator will be indeterminate.
     */
    public ProgressIndicator(boolean indeterminate) {
        setUIID("ProgressIndicator");
        this.indeterminate = indeterminate;
        indeterminateStyle = UIManager.getInstance().getComponentStyle("ProgressIndicator");
        image = indeterminateStyle.getBgImage();
        rotatedImage = image;

        progressBar = new Slider();
        progressBar.setInfinite(false);
        progressBar.setEditable(false);

        this.setFocusable(false);
    }

    /**
     * @inheritDoc
     */
    public void initComponent() {
        if (indeterminate) {
            getComponentForm().registerAnimated(this);
        }
    }

    /**
     * @inheritDoc
     */
    public void deinitialize() {
        if (indeterminate) {
            Form f = getComponentForm();
            if (f != null) {
                f.deregisterAnimated(this);
            }
        }
    }

    /**
     * Sets the value of the progress bar.
     *
     * @param value value
     */
    public void setValue(int value) {
        progressBar.setSliderValue(value);
        repaint();
    }

    /**
     * Indicates the value of the progress bar.
     *
     * @return the value of the progress bar.
     */    
    public int getValue() {
        return progressBar.getSliderValue();
    }
    
    /**
     * Returns the maximum value set for the progress bar. Default value is 100.
     *
     * @return the maximum value
     */
    public int getMaxValue() {
        return progressBar.getMaxValue();
    }

    /**
     * Indicates the maximum value set for the progress bar.
     *
     * @param maxValue the maxValue to set
     */
    public void setMaxValue(int maxValue) {
        progressBar.setMaxValue(maxValue);
        repaint();
    }

    /**
     * Returns the minimum value set for the progress bar. Default value is 0.
     *
     * @return the minimum value
     */
    public int getMinValue() {
        return progressBar.getMinValue();
    }

    /**
     * Indicates the minimum value set for the progress bar.
     *
     * @param minValue the minimum value
     */
    public void setMinValue(int minValue) {
        progressBar.setMinValue(minValue);
        repaint();
    }    

    /**
     * @return the renderValueOnTop
     */
    public boolean isRenderValueOnTop() {
        return progressBar.isRenderValueOnTop();
    }

    /**
     * Indicates that the percentage value of the progress bar should be
     * rendered with percentage sign on top of the progress bar. For indefinite
     * progress indicator this method will have no effect.
     *
     * @param renderPercentageOnTop true to render percentages
     */
    public void setRenderPercentageOnTop(boolean renderPercentageOnTop) {
        progressBar.setRenderPercentageOnTop(renderPercentageOnTop);
        repaint();
    }
    
    /**
     * @return true if percentage value is rendered
     */
    public boolean isRenderPercentageOnTop() {
        return progressBar.isRenderPercentageOnTop();
    }

    /**
     * Indicates that the value of the progress bar should be rendered on top
     * of the progress bar. For indeterminate progress indicator this method
     * will have no effect.
     *
     * @param renderValueOnTop true to render value
     */
    public void setRenderValueOnTop(boolean renderValueOnTop) {
        progressBar.setRenderValueOnTop(renderValueOnTop);
        repaint();
    }
    
    /**
     * Tells whether the progress indicator is in indeterminate or determinate
     * mode.
     *
     * @return true if the progress indicator is in indeterminate mode.
     */ 
    public boolean isIndeterminate() {
        return indeterminate;
    }

    /**
     * Sets the mode of the progress indicator.
     *
     * @param indeterminate if true the progress indicator will be set to 
     * indeterminate mode.
     */ 
    public void setIndeterminate(boolean indeterminate) {
        this.indeterminate = indeterminate;
    }

    /**
     * @inheritDoc
     */
    public void setX(int x) {
        progressBar.setX(x);
        super.setX(x);
    }

    /**
     * @inheritDoc
     */
    public void setY(int y) {
        progressBar.setY(y);
        super.setY(y);
    }

    /**
     * @inheritDoc
     */
    public void setWidth(int w) {
        progressBar.setWidth(w);
        super.setWidth(w);
    }

    /**
     * @inheritDoc
     */
    public void setHeight(int h) {
        progressBar.setHeight(h);
        super.setHeight(h);
    }

    /**
     * @inheritDoc
     */
    public void setSize(Dimension dim) {
        progressBar.setSize(dim);
        super.setSize(dim);
    }

    /**
     * @inheritDoc
     */
    public boolean animate() {
        long curTime = System.currentTimeMillis();
        long delta = curTime - timeStamp;

        if (image != null && delta > MILLISECONDS_BETWEEN_UPDATES) {
            angle += 45;
            if (angle >= 360) {
                angle -= 360;
            }
            rotatedImage = image.rotate((int) angle);
            timeStamp = curTime;
            return true; // repaint is needed
        }
        return false; // no repaint needed
    }

    /**
     * @inheritDoc
     */
    public void paintBackground(Graphics g) {
        if (!indeterminate) {
            progressBar.paintBackground(g);
        }
    }

    /**
     * @inheritDoc
     */
    public void paint(Graphics g) {
        if (indeterminate && rotatedImage != null) {
            int x = getX() + (getWidth() / 2) - (rotatedImage.getWidth() / 2);
            int y = getY() + (getHeight() / 2) - (rotatedImage.getHeight() / 2);
            g.drawImage(rotatedImage, x, y);
        }
        else {
            progressBar.paint(g);
        }
    }

    /**
     * @inheritDoc
     */
    public Dimension calcPreferredSize() {
        if (indeterminate && image != null) {
            int prefW = 0, prefH = 0;
            if (indeterminateStyle.getBorder() != null) {
                prefW = Math.max(indeterminateStyle.getBorder().getMinimumWidth(), prefW);
                prefH = Math.max(indeterminateStyle.getBorder().getMinimumHeight(), prefH);
            }
            prefW += image.getWidth();
            prefH += image.getHeight();
            return new Dimension(prefW, prefH);
        } else {
            if (progressBar != null) {
                return progressBar.getPreferredSize();
            } else {
                return super.calcPreferredSize();
            }
        }
    }
}
