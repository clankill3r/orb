package nl.doekewartena.orb.inner_core.util.datatstructure;


/**
 * Created by doekewartena on 8/29/15.
 */
public interface _TreeSettings<T>  { //extends _NewInstance<_Data<T, ?>> { // , C extends _TreeSettings

//    default int maxObjects() {
//        return 64;
//    }
//
//    default int maxLevels() {
//        return 64;
//    }

    int getMaxObjects();
    int getMaxLevels();

    // return C?
    void setMaxObjects(int max);
    void setMaxLevels(int max);





    // this is misleading, by method name it implies we get a new instance of TreeSetting / the current class
    // while in reality we want a _Data!
    //@Override
    //_Data<T, ?> newInstance();

    // 1
    // shouldn't this extending be optional?
    // we don;t make use of it in the core
    _Data<T, ? extends _Data> createDataInstance();


}
