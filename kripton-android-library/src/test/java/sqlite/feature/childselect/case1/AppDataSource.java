package sqlite.feature.childselect.case1;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet={DaoAlbum.class, DaoSong.class}, fileName="app.db")
public interface AppDataSource {

}
