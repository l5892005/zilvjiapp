package com.rongwei.fastcodeaccumulate.data.param;

public class InserFastCodeBean {
    String name ;
    int type;
    int typedesc;
    String content;
    int importd;

    public InserFastCodeBean() {
    }

    public InserFastCodeBean(String name, int type, int typedesc, String content, int importd) {
        this.name = name;
        this.type = type;
        this.typedesc = typedesc;
        this.content = content;
        this.importd = importd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTypedesc() {
        return typedesc;
    }

    public void setTypedesc(int typedesc) {
        this.typedesc = typedesc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImportd() {
        return importd;
    }

    public void setImportd(int importd) {
        this.importd = importd;
    }
}
