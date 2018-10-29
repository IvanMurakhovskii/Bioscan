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
    final RealmObjectSchema schemaDataSensor = schema.get("DataSensor");

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

    if(oldVersion == 4) {

      userSchema.addField("isFullData", boolean.class);
      userSchema.transform(obj -> obj.setBoolean("isFullData", false));
      RealmObjectSchema schema1 = schema.create("RealmInt")
          .addField("value", int.class);
      schemaDataSensor.addRealmListField("dataSens1_tmp", schema1);
      schemaDataSensor.addRealmListField("dataSens2_tmp", schema1);
      schemaDataSensor.addRealmListField("dataSens3_tmp", schema1);
      schemaDataSensor.addRealmListField("dataSens4_tmp", schema1);
      schemaDataSensor.addRealmListField("dataSens5_tmp", schema1);
      schemaDataSensor.addRealmListField("dataSens6_tmp", schema1);
      schemaDataSensor.addRealmListField("dataSens7_tmp", schema1);
      schemaDataSensor.addRealmListField("dataSens8_tmp", schema1);

      try {
        schemaDataSensor.transform(obj -> {

          DynamicRealmObject ri1 = realm.createObject("RealmInt");
          ri1.setInt("value", obj.getInt("dataSens1"));
          obj.getList("dataSens1_tmp").add(ri1);

          DynamicRealmObject ri2 = realm.createObject("RealmInt");
          ri2.setInt("value", obj.getInt("dataSens2"));
          obj.getList("dataSens2_tmp").add(ri2);

          DynamicRealmObject ri3 = realm.createObject("RealmInt");
          ri3.setInt("value", obj.getInt("dataSens3"));
          obj.getList("dataSens3_tmp").add(ri3);

          DynamicRealmObject ri4 = realm.createObject("RealmInt");
          ri4.setInt("value", obj.getInt("dataSens4"));
          obj.getList("dataSens4_tmp").add(ri4);

          DynamicRealmObject ri5 = realm.createObject("RealmInt");
          ri5.setInt("value", obj.getInt("dataSens5"));
          obj.getList("dataSens5_tmp").add(ri5);

          DynamicRealmObject ri6 = realm.createObject("RealmInt");
          ri6.setInt("value", obj.getInt("dataSens6"));
          obj.getList("dataSens6_tmp").add(ri6);

          DynamicRealmObject ri7 = realm.createObject("RealmInt");
          ri7.setInt("value", obj.getInt("dataSens7"));
          obj.getList("dataSens7_tmp").add(ri7);

          DynamicRealmObject ri8 = realm.createObject("RealmInt");
          ri8.setInt("value", obj.getInt("dataSens8"));
          obj.getList("dataSens8_tmp").add(ri8);
        })
            .removeField("dataSens1")
            .removeField("dataSens2")
            .removeField("dataSens3")
            .removeField("dataSens4")
            .removeField("dataSens5")
            .removeField("dataSens6")
            .removeField("dataSens7")
            .removeField("dataSens8")
            .renameField("dataSens1_tmp", "dataSens1")
            .renameField("dataSens2_tmp", "dataSens2")
            .renameField("dataSens3_tmp", "dataSens3")
            .renameField("dataSens4_tmp", "dataSens4")
            .renameField("dataSens5_tmp", "dataSens5")
            .renameField("dataSens6_tmp", "dataSens6")
            .renameField("dataSens7_tmp", "dataSens7")
            .renameField("dataSens8_tmp", "dataSens8");

        oldVersion++;
      } catch (Exception e) {
        e.getStackTrace();
      }
    }

    if (oldVersion < newVersion) {
          throw new IllegalStateException(String.format(Locale.US, "Migration missing from v%d to v%d" , oldVersion, newVersion));
    }
  }
}
