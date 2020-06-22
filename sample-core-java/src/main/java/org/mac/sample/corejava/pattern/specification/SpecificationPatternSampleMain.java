package org.mac.sample.corejava.pattern.specification;

public class SpecificationPatternSampleMain {

    public static void main(String[] args) {

        QueryCriteria brandIsHuawei = new FunctionalQueryCriteria<MobilePhone,Boolean>(
                mp -> Brand.HUAWEI.equals(mp.getBrand())
        );
        QueryCriteria isSmartMobilePhone = new FunctionalQueryCriteria<MobilePhone,Boolean>(
                mp -> Type.SMART.equals(mp.getType())
        );

        QueryCriteria compositeCriteria = brandIsHuawei.and(isSmartMobilePhone);

        System.out.println(compositeCriteria.isMatched(new MobilePhone(Brand.HUAWEI,Type.SMART)));
    }
}
enum Brand {
    APPLE,HUAWEI,SAMSUNG;
}

enum Type {
    SMART,BASIC;
}

class MobilePhone {

    private Brand brand;
    private Type  type;

    public MobilePhone(Brand brand, Type type) {
        this.brand = brand;
        this.type = type;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
