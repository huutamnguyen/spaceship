package main.constants;

public class Constants
{
  private Constants()
  {
    super();
  }
  public static final int WALL_SPACE = 1;
  public static final int BOARD_WITH = 360 + 2 * WALL_SPACE;
  public static final int BOARD_HEIGHT = 600 + 2 * WALL_SPACE;
  public static final int STEP_DISTANCE = 20;
  public static final int WALL_SIZE = 30;
  public static final int X_BEGIN = WALL_SIZE + WALL_SPACE;
  public static final int Y_BEGIN = WALL_SIZE + WALL_SPACE;
  public static final int X_END = BOARD_WITH - WALL_SIZE - WALL_SPACE;
  public static final int Y_END = BOARD_HEIGHT - WALL_SIZE - WALL_SPACE;
  public static final int BOAT_POSITION_Y = Y_END - STEP_DISTANCE;
  public static final int BOAT_POSITION_X_BEGIN = (BOARD_WITH - 2 * WALL_SPACE) / 2 - 10 + 1;
  public static final int BOARD_TIME_DELAY = 1000;
  public static final int BOAT_TIME_DELAY = 60;
  public static final int BULLET_TIME_DELAY = 30;
  public static final int ENEMY_TIME_DELAY = 100;
  public static final int READY_TIME_DELAY = 300;
  public static final int ENEMY_ENVIRONMENT_DELAY = 60;
  public static final String LEFT = "LEFT";
  public static final String RIGHT = "RIGHT";
  public static final String PLAY_AGAIN_MESSAGE = "< Press enter to play again >";
  public static final int READY_LENGTH = 240;
}
