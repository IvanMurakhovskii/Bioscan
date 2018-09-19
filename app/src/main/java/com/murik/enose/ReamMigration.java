package com.murik.enose;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;
import java.util.Locale;

public class ReamMigration implements RealmMigration {

  @Override
  public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
    final RealmSchema schema = realm.getSchema();
    final RealmObjectSchema userSchema = schema.get("DataSensorRealm");

    if(oldVersion == 0){
     try{
       userSchema.addField("isPractice", boolean.class);
       oldVersion++;
      }catch (Exception e){
       e.getStackTrace();
     }
    }
    if(oldVersion == 1) {

      try {
        userSchema.addField("time", long.class);
        oldVersion++;
      } catch (Exception e) {
        e.getStackTrace();
      }
    }

    if(oldVersion == 2){

      try {
        RealmObjectSchema schemeDataSensor = schema.create("DataSensor")
            .addField("dataSens1", int.class)
            .addField("dataSens2", int.class)
            .addField("dataSens3", int.class)
            .addField("dataSens4", int.class)
            .addField("dataSens5", int.class)
            .addField("dataSens6", int.class)
            .addField("dataSens7", int.class)
            .addField("dataSens8", int.class);

        userSchema.addRealmObjectField("leftHandData", schemeDataSensor)
                  .addRealmObjectField("rightHandData", schemeDataSensor)
                  .addField("gender", int.class)
                  .addField("hand", int.class);

        oldVersion++;
      } catch (Exception e) {
        e.getStackTrace();
      }
    }
    if(oldVersion == 3) {

      try {
        userSchema.transform(obj -> {
          DynamicRealmObject leftHand = realm.createObject("DataSensor");
          leftHand.setInt("dataSens1", obj.getInt("dataSens1"));
          leftHand.setInt("dataSens2", obj.getInt("dataSens2"));
          leftHand.setInt("dataSens3", obj.getInt("dataSens3"));
          leftHand.setInt("dataSens4", obj.getInt("dataSens4"));
          leftHand.setInt("dataSens5", obj.getInt("dataSens5"));
          leftHand.setInt("dataSens6", obj.getInt("dataSens6"));
          leftHand.setInt("dataSens7", obj.getInt("dataSens7"));
          leftHand.setInt("dataSens8", obj.getInt("dataSens8"));
          obj.set("leftHandData", leftHand);
        })
            .removeField("dataSens1")
            .removeField("dataSens2")
            .removeField("dataSens3")
            .removeField("dataSens4")
            .removeField("dataSens5")
            .removeField("dataSens6")
            .removeField("dataSens7")
            .removeField("dataSens8");

        oldVersion++;
      } catch (Exception e) {
        e.getStackTrace();
      }
    }

    if (oldVersion < newVersion) {
          throw new IllegalStateException(String.format(Locale.US, "Migration missing from v%d to v%d", oldVersion, newVersion));
    }
  }
}
