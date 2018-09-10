package com.murik.enose;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class ReamMigration implements RealmMigration {

  @Override
  public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
    final RealmSchema schema = realm.getSchema();

    if(oldVersion == 0){
     final RealmObjectSchema userSchema = schema.get("DataSensorRealm");
     try{
       userSchema.addField("isPractice", boolean.class);
      }catch (Exception e){
       e.getStackTrace();
     }

    } else if(oldVersion == 1) {
      final RealmObjectSchema userSchema = schema.get("DataSensorRealm");
      try {
        userSchema.addField("time", long.class);
        } catch (Exception e) {
        e.getStackTrace();
      }
    }
  }
}
