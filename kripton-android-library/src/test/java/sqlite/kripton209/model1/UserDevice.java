package sqlite.kripton209.model1;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.android.sqlite.ForeignKeyAction;

/**
 * <p>
 * Generated entity implementation for <code>UserDevice</code>
 * </p>
 */
@BindTable(
    name = "user_device"
)
public class UserDevice {
  /**
   * Primary key
   */
  @BindSqlColumn(
      columnType = ColumnType.PRIMARY_KEY
  )
  public long id;

  /**
   * Foreign key to User model class
   */
  @BindSqlColumn(
      parentEntity = User.class,
      onDelete = ForeignKeyAction.CASCADE
  )
  public long userId;

  /**
   * Foreign key to Device model class
   */
  @BindSqlColumn(
      parentEntity = Device.class,
      onDelete = ForeignKeyAction.CASCADE
  )
  public long deviceId;
}
