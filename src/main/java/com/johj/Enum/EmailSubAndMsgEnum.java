package com.johj.Enum;

/**
 * Email主题、内容模版枚举，参数用{0}、{1}占位表示，使用MessageFormat.format(EmailSubAndMsgEnum.Test.getSubject(),new Object[]{"100"})进行格式化
 * Created by zhouwenwei on 01/08/2017.
 */
public enum EmailSubAndMsgEnum {
    Test("code，样本状态更新失败","失败原因：{0}");

    /**
     * @subject: 发送邮件的主题
     * @msg: 发送邮件的内容
     */

    private String subject;
    private String msg;

    public String getSubject() {
        return subject;
    }

    public String getMsg() {
        return msg;
    }

    EmailSubAndMsgEnum(String subject, String msg) {
        this.subject = subject;
        this.msg = msg;
    }
}
