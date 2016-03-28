package cubex2.cxlibrary.gui.control;

import org.lwjgl.util.Rectangle;

public class Anchor
{
    private int width;
    private int height;

    private int distanceLeft;
    private int distanceRight;
    private int distanceTop;
    private int distanceBottom;

    /**
     * If true, left side is anchored at left side of other control instead of the right side.
     */
    private boolean sameSideLeft = false;
    /**
     * If true, left side is anchored at right side of other control instead of the left side.
     */
    private boolean sameSideRight = false;
    /**
     * If true, left side is anchored at top side of other control instead of the bottom side.
     */
    private boolean sameSideTop = false;
    /**
     * If true, left side is anchored at bottom side of other control instead of the top side.
     */
    private boolean sameSideBottom = false;

    private Control controlLeft = null;
    private Control controlRight = null;
    private Control controlTop = null;
    private Control controlBottom = null;

    public Anchor left(Control c, int dist, boolean sameSide)
    {
        controlLeft = c;
        distanceLeft = dist;
        sameSideLeft = sameSide;
        return this;
    }

    public Anchor right(Control c, int dist, boolean sameSide)
    {
        controlRight = c;
        distanceRight = dist;
        sameSideRight = sameSide;
        return this;
    }

    public Anchor top(Control c, int dist, boolean sameSide)
    {
        controlTop = c;
        distanceTop = dist;
        sameSideTop = sameSide;
        return this;
    }

    public Anchor bottom(Control c, int dist, boolean sameSide)
    {
        controlBottom = c;
        distanceBottom = dist;
        sameSideBottom = sameSide;
        return this;
    }

    public Anchor size(int w, int h)
    {
        width = w;
        height = h;
        return this;
    }

    public Anchor centerHorIn(Control c)
    {
        return left(c, 0, true).right(c, 0, true);
    }

    public Anchor centerVertIn(Control c)
    {
        return top(c, 0, true).bottom(c, 0, true);
    }

    public Anchor centerHorBetween(Control left, Control right)
    {
        return left(left, 0, false).right(right, 0, false);
    }

    public Anchor centerVertBetween(Control top, Control bottom)
    {
        return top(top, 0, false).bottom(bottom, 0, false);
    }

    public Rectangle apply()
    {
        int x = 0;
        int y = 0;
        int w = width;
        int h = height;

        if (left() && right())
        {
            if (w > 0)
            {
                x = getAnchorLeft() + (getAnchorRight() - getAnchorLeft() - w) / 2;
            } else
            {
                x = getAnchorLeft() + distanceLeft;
                w = getAnchorRight() - distanceRight - x;
            }
        } else if (left())
        {
            x = getAnchorLeft() + distanceLeft;
        } else if (right())
        {
            x = getAnchorRight() - distanceRight - w;
        }

        if (top() && bottom())
        {
            if (h > 0)
            {
                y = getAnchorTop() + (getAnchorBottom() - getAnchorTop() - h) / 2;
            } else
            {
                y = getAnchorTop() + distanceTop;
                h = getAnchorBottom() - distanceBottom - y;
            }
        } else if (top())
        {
            y = getAnchorTop() + distanceTop;
        } else if (bottom())
        {
            y = getAnchorBottom() - distanceBottom - h;
        }

        return new Rectangle(x, y, w, h);
    }

    private boolean left()
    {
        return controlLeft != null;
    }

    private boolean right()
    {
        return controlRight != null;
    }

    private boolean top()
    {
        return controlTop != null;
    }

    private boolean bottom()
    {
        return controlBottom != null;
    }

    public int getAnchorLeft()
    {
        return controlLeft.getBounds().getX() + (sameSideLeft ? 0 : controlLeft.getBounds().getWidth());
    }

    public int getAnchorRight()
    {
        return controlRight.getBounds().getX() + (sameSideRight ? controlRight.getBounds().getWidth() : 0);
    }

    public int getAnchorTop()
    {
        return controlTop.getBounds().getY() + (sameSideTop ? 0 : controlTop.getBounds().getHeight());
    }

    public int getAnchorBottom()
    {
        return controlBottom.getBounds().getY() + (sameSideBottom ? controlBottom.getBounds().getHeight() : 0);
    }
}
