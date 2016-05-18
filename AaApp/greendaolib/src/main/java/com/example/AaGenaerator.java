package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class AaGenaerator {

    public static void main(String[] args) throws Exception {
        AaGenaerator testDaoGenerator = new AaGenaerator();
        testDaoGenerator.generate();
    }

    private final Schema schema;
    private final Entity userEntity = null;
    private final Entity accountBook= null;
    private final Entity categoryEntity= null;
    private final Entity payoutEntity= null;


    public AaGenaerator() {
        schema = new Schema(1, "com.example.aa.aaapp.greendao.model");
        schema.setDefaultJavaPackageTest("de.greenrobot.daotest.entity");
        createModelUser();
        createAccountBook();
        createcategoryEntity();
        createPayoutEntity();

    }

    public void generate() throws Exception {
        DaoGenerator daoGenerator = new DaoGenerator();
        daoGenerator.generateAll(schema, "./app/src/main/java/");

    }

    protected Entity createModelUser() {
        Entity userEntity = schema.addEntity("UserEntity");
        userEntity.setJavaDoc("This entity is used by internal tests of greenDAO.\n" +
                "(This JavaDoc is defined in the generator project.)");
        userEntity.setCodeBeforeClass("// This is another test comment, you could also apply annotations like this");
        userEntity.addIdProperty().javaDocField("JavaDoc test field");

        userEntity.addIntProperty("userId").index();
        userEntity.addStringProperty("userStatus").notNull().javaDocGetterAndSetter("JavaDoc test getter and setter");
        userEntity.addIntProperty("userName").notNull().javaDocGetterAndSetter("JavaDoc test getter");
        userEntity.addDateProperty("createDate").notNull();
        return userEntity;
    }

    protected Entity createAccountBook() {
        Entity accountBook = schema.addEntity("AccountBookEntity");
        accountBook.setJavaDoc("This entity is used for AccountBook.\n" +
                "(This JavaDoc is defined in the generator project.)");
        accountBook.setCodeBeforeClass("// This is another test comment, you could also apply annotations like this");
        accountBook.addIdProperty().javaDocField("JavaDoc test field");

        accountBook.addIntProperty("accountBookId").index();
        accountBook.addStringProperty("state").notNull().javaDocGetterAndSetter("JavaDoc test getter and setter");
        accountBook.addIntProperty("isDefault").notNull().javaDocGetterAndSetter("JavaDoc test getter");
        accountBook.addDateProperty("createDate").notNull();
        return accountBook;
    }


    protected Entity createcategoryEntity() {
        Entity accountBook = schema.addEntity("CategoryEntity");
        accountBook.setJavaDoc("This entity is used for categoryEntity.\n" +
                "(This JavaDoc is defined in the generator project.)");
        accountBook.setCodeBeforeClass("// This is another test comment, you could also apply annotations like this");
        accountBook.addIdProperty().javaDocField("JavaDoc test field");

        accountBook.addIntProperty("categoryId").index();
        accountBook.addStringProperty("categoryName").notNull().javaDocGetterAndSetter("JavaDoc test getter and setter");
        accountBook.addIntProperty("typeFlag").notNull().javaDocGetterAndSetter("JavaDoc test getter");
        accountBook.addIntProperty("parentId").notNull().javaDocGetterAndSetter("JavaDoc test getter");
        accountBook.addStringProperty("path").notNull().javaDocGetterAndSetter("JavaDoc test getter");
        accountBook.addIntProperty("state").notNull().javaDocGetterAndSetter("JavaDoc test getter");
        accountBook.addDateProperty("createDate").notNull();
        return accountBook;
    }

    protected Entity createPayoutEntity() {
        Entity payoutEntity = schema.addEntity("PayoutEntity");
        payoutEntity.setJavaDoc("This entity is used for payoutEntity.\n" +
                "(This JavaDoc is defined in the generator project.)");
        payoutEntity.setCodeBeforeClass("// This is another test comment, you could also apply annotations like this");
        payoutEntity.addIdProperty().javaDocField("JavaDoc test field");

        payoutEntity.addIntProperty("payoutId").index();
        payoutEntity.addIntProperty("accountBookId").notNull();
        payoutEntity.addStringProperty("accountBookName").notNull();
        payoutEntity.addIntProperty("categoryId").notNull();
        payoutEntity.addStringProperty("categoryName").notNull().javaDocGetterAndSetter("JavaDoc test getter and setter");
        payoutEntity.addStringProperty("path").notNull();
        payoutEntity.addIntProperty("payWayId").notNull();
        payoutEntity.addIntProperty("placeId").notNull();
        payoutEntity.addLongProperty("amount").notNull();
        payoutEntity.addDateProperty("payoutDate").notNull();
        payoutEntity.addStringProperty("payoutType").notNull();
        payoutEntity.addIntProperty("payoutUserId").notNull();
        payoutEntity.addStringProperty("comment").notNull();
        payoutEntity.addDateProperty("createDate").notNull();
        payoutEntity.addIntProperty("state").notNull();

        return accountBook;
    }






}
