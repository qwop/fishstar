package com.tan.test.bean;


import java.io.Serializable;

public class SIInfo implements Serializable
{
    private String openflag; // 0_OP审核通过 1_OP开通
    private String strspid; // 合作伙伴ID
    private String strname; // 合作伙伴全称
    private String strshortname; // 合作伙伴简称
    private String intsptype; // 合作伙伴类型
    private String strsptype; // 合作伙伴类型
    private String dtopentime; // 开通时间
    private String stropenman; // 开通人
    private String intstatus; // 状态值. 7非正式
    private String dtstatuschangetime; // 状态改变时间
    private String strpostcode; // 邮政编码
    private String strlinkman; // 联系人
    private String strlinkno; // 联系电话
    private String strcustomermgr; // 客户经理
    private String strcustomermgrno; // 客户经理号码
    private String strfax; // 传真号码
    private String stremail; // 邮箱地址
    private String strdesc; // 合作伙伴描述
    private String strprofile; // 合作伙伴信息资料
    private String strsplogourl; // 合作伙伴Logo URL
    private String strspbitmapurl; // 合作伙伴图片 URL
    private String strareapath; // 地区路径
    // private String strareaname;// 区域名称（所属区域）

    private String strsharekey; // 公钥
    private String strspcode; // 企业代码
    private String strspaccessno; // 接入号码;基本接入号
    private String strsppaperid; // 增值业务许可证号

    private String intsplevel; // SI级别
    private String dtdisabletime; // SI退出时间
    private String strenglishname; // 公司英文
    private String intapplytype; // 申请类型
    private String strindustry; // 专注行业
    private String strservicedesc; // 主要产品或业务描述
    private String strstrengthdesc; // 行业资源或实力描述
    private String straddress; // 公司地址
    private String stropenaccountbank; // 开户银行
    private String strbankaccount; // 银行帐号
    private String strpubservicephone; // 公共服务电话
    private String strpubservicefax; // 公共服务传真
    private String strprofilefir; // 07.4.18 扩展字段 文件附件1
    private String strprofilesec; // 07.4.18 扩展字段 文件附件2

    private String strpaltype; // 合作伙伴属性
    private String straccountname; // 开户名称
    private String strloginfund; // 注册资金
    private String strbusinessdate; // 营业执照有效期
    private String strlogintaxcard; // 税务登记证
    private String strbusinesscard; // 营业执照
    private String strdeputy; // 法人代表

    /**
     * 关联： strspid; //合作伙伴ID 联系人信息
     * 
     */
    private String strleaderman; // 公司领导姓名
    private String strleadermobile; // 公司领导手机号码
    private String strleaderemail; // 公司领导Email
    private String strleaderfax; // 公司领导传真
    private String strleaderprincipalship; // 公司领导职务
    private String strserviceman; // 客服人员姓名
    private String strservicemobile; // 客服人员手机
    private String strserviceemail; // 客服人员Eamil
    private String strservicefax; // 客服人员传真
    private String strpriserviceman; // 主要业务联系人
    private String strpriservicemobile; // 主要业务联系人手机
    private String strpriserviceemail; // 主要业务联系人Email
    private String strpriservicefax; // 主要业务联系人传真
    private String strpriserviceprincipalship; // 主要业务联系人职务
    private String strsecserviceman; // 次要业务联系人
    private String strsecservicemobile; // 次要业务联系人手机
    private String strsecserviceemail; // 次要业务联系人Email
    private String strsecservicefax; // 次要业务联系人传真
    private String strsecserviceprincipalship; // 次要业务联系人职务
    private String strtechlinkman; // 主要技术联系人
    private String strtechlinkmanmobile; // 主要技术联系人手机
    private String strtechlinkmanemail; // 主要技术联系人Email
    private String strtechlinkmanfax; // 主要技术联系人传真
    private String strtechlinkmanprincipalship; // 主要技术联系人职务
    private String strsectechlinkman; // 次要技术联系人
    private String strsectechlinkmanmobile; // 次要技术联系人手机
    private String strsectechlinkmanemail; // 次要技术联系人Email
    private String strsectechlinkmanfax; // 次要技术联系人传真
    private String strseclinkmanprincipalship; // 次要技术联系人职务
    private String intsiomsflag; // 是否产生OMS文档

    private String strspadminid; // 管理员ID

    /**
     * 以下是关于管理员的定义
     */
    private String adminid; // 管理员ID
    private String adminname; // 管理员显示名称
    private String adminpassword; // 登录密码
    private String adminstatus; // 状态值
    private String statuschangetime; // 状态变更时间
    private String strphoneno; // 电话号码
    private String adminemail; // Email
    private String adminmobile; // 手机号码


    public String getOpenflag() {
        return openflag;
    }

    public void setOpenflag(String openflag) {
        this.openflag = openflag;
    }


    public String getDtopentime() {
        return dtopentime;
    }

    public void setDtopentime(String dtopentime) {
        this.dtopentime = dtopentime;
    }

    public String getDtstatuschangetime() {
        return dtstatuschangetime;
    }

    public void setDtstatuschangetime(String dtstatuschangetime) {
        this.dtstatuschangetime = dtstatuschangetime;
    }

    public String getIntsptype() {
        return intsptype;
    }

    public void setIntsptype(String intsptype) {
        this.intsptype = intsptype;
    }

    public String getIntstatus() {
        return intstatus;
    }

    public void setIntstatus(String intstatus) {
        this.intstatus = intstatus;
    }

    public String getStrareapath() {
        return strareapath;
    }

    public void setStrareapath(String strareapath) {
        this.strareapath = strareapath;
    }

    public String getStrcustomermgr() {
        return strcustomermgr;
    }

    public void setStrcustomermgr(String strcustomermgr) {
        this.strcustomermgr = strcustomermgr;
    }

    public String getStrcustomermgrno() {
        return strcustomermgrno;
    }

    public void setStrcustomermgrno(String strcustomermgrno) {
        this.strcustomermgrno = strcustomermgrno;
    }

    public String getStrdesc() {
        return strdesc;
    }

    public void setStrdesc(String strdesc) {
        this.strdesc = strdesc;
    }

    public String getStremail() {
        return stremail;
    }

    public void setStremail(String stremail) {
        this.stremail = stremail;
    }

    public String getStrfax() {
        return strfax;
    }

    public void setStrfax(String strfax) {
        this.strfax = strfax;
    }

    public String getStrlinkman() {
        return strlinkman;
    }

    public void setStrlinkman(String strlinkman) {
        this.strlinkman = strlinkman;
    }

    public String getStrlinkno() {
        return strlinkno;
    }

    public void setStrlinkno(String strlinkno) {
        this.strlinkno = strlinkno;
    }

    public String getStrname() {
        return strname;
    }

    public void setStrname(String strname) {
        this.strname = strname;
    }

    public String getStropenman() {
        return stropenman;
    }

    public void setStropenman(String stropenman) {
        this.stropenman = stropenman;
    }

    public String getStrpostcode() {
        return strpostcode;
    }

    public void setStrpostcode(String strpostcode) {
        this.strpostcode = strpostcode;
    }

    public String getStrprofile() {
        return strprofile;
    }

    public void setStrprofile(String strprofile) {
        this.strprofile = strprofile;
    }

    public String getStrsharekey() {
        return strsharekey;
    }

    public void setStrsharekey(String strsharekey) {
        this.strsharekey = strsharekey;
    }

    public String getStrshortname() {
        return strshortname;
    }

    public void setStrshortname(String strshortname) {
        this.strshortname = strshortname;
    }

    public String getStrspaccessno() {
        return strspaccessno;
    }

    public void setStrspaccessno(String strspaccessno) {
        this.strspaccessno = strspaccessno;
    }

    public String getStrspbitmapurl() {
        return strspbitmapurl;
    }

    public void setStrspbitmapurl(String strspbitmapurl) {
        this.strspbitmapurl = strspbitmapurl;
    }

    public String getStrspcode() {
        return strspcode;
    }

    public void setStrspcode(String strspcode) {
        this.strspcode = strspcode;
    }

    public String getStrsplogourl() {
        return strsplogourl;
    }

    public void setStrsplogourl(String strsplogourl) {
        this.strsplogourl = strsplogourl;
    }

    public String getAdminemail() {
        return adminemail;
    }

    public void setAdminemail(String adminemail) {
        this.adminemail = adminemail;
    }

    public String getAdminid() {
        return adminid;
    }

    public void setAdminid(String adminid) {
        this.adminid = adminid;
    }

    public String getAdminmobile() {
        return adminmobile;
    }

    public void setAdminmobile(String adminmobile) {
        this.adminmobile = adminmobile;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getAdminpassword() {
        return adminpassword;
    }

    public void setAdminpassword(String adminpassword) {
        this.adminpassword = adminpassword;
    }

    public String getAdminstatus() {
        return adminstatus;
    }

    public void setAdminstatus(String adminstatus) {
        this.adminstatus = adminstatus;
    }

    public String getStatuschangetime() {
        return statuschangetime;
    }

    public void setStatuschangetime(String statuschangetime) {
        this.statuschangetime = statuschangetime;
    }

    public String getStrphoneno() {
        return strphoneno;
    }

    public void setStrphoneno(String strphoneno) {
        this.strphoneno = strphoneno;
    }

    /**
     * @return the dtdisabletime
     */
    public String getDtdisabletime() {
        return dtdisabletime;
    }

    /**
     * @param dtdisabletime
     *            the dtdisabletime to set
     */
    public void setDtdisabletime(String dtdisabletime) {
        this.dtdisabletime = dtdisabletime;
    }

    /**
     * @return the intapplytype
     */
    public String getIntapplytype() {
        return intapplytype;
    }

    /**
     * @param intapplytype
     *            the intapplytype to set
     */
    public void setIntapplytype(String intapplytype) {
        this.intapplytype = intapplytype;
    }

    /**
     * @return the intsplevel
     */
    public String getIntsplevel() {
        return intsplevel;
    }

    /**
     * @param intsplevel
     *            the intsplevel to set
     */
    public void setIntsplevel(String intsplevel) {
        this.intsplevel = intsplevel;
    }

    /**
     * @return the straddress
     */
    public String getStraddress() {
        return straddress;
    }

    /**
     * @param straddress
     *            the straddress to set
     */
    public void setStraddress(String straddress) {
        this.straddress = straddress;
    }

    /**
     * @return the strbankaccount
     */
    public String getStrbankaccount() {
        return strbankaccount;
    }

    /**
     * @param strbankaccount
     *            the strbankaccount to set
     */
    public void setStrbankaccount(String strbankaccount) {
        this.strbankaccount = strbankaccount;
    }

    /**
     * @return the strenglishname
     */
    public String getStrenglishname() {
        return strenglishname;
    }

    /**
     * @param strenglishname
     *            the strenglishname to set
     */
    public void setStrenglishname(String strenglishname) {
        this.strenglishname = strenglishname;
    }

    /**
     * @return the stropenaccountbank
     */
    public String getStropenaccountbank() {
        return stropenaccountbank;
    }

    /**
     * @param stropenaccountbank
     *            the stropenaccountbank to set
     */
    public void setStropenaccountbank(String stropenaccountbank) {
        this.stropenaccountbank = stropenaccountbank;
    }

    /**
     * @return the strpubservicefax
     */
    public String getStrpubservicefax() {
        return strpubservicefax;
    }

    /**
     * @param strpubservicefax
     *            the strpubservicefax to set
     */
    public void setStrpubservicefax(String strpubservicefax) {
        this.strpubservicefax = strpubservicefax;
    }

    /**
     * @return the strpubservicephone
     */
    public String getStrpubservicephone() {
        return strpubservicephone;
    }

    /**
     * @param strpubservicephone
     *            the strpubservicephone to set
     */
    public void setStrpubservicephone(String strpubservicephone) {
        this.strpubservicephone = strpubservicephone;
    }

    /**
     * @return the strservicedesc
     */
    public String getStrservicedesc() {
        return strservicedesc;
    }

    /**
     * @param strservicedesc
     *            the strservicedesc to set
     */
    public void setStrservicedesc(String strservicedesc) {
        this.strservicedesc = strservicedesc;
    }

    /**
     * @return the strstrengthdesc
     */
    public String getStrstrengthdesc() {
        return strstrengthdesc;
    }

    /**
     * @param strstrengthdesc
     *            the strstrengthdesc to set
     */
    public void setStrstrengthdesc(String strstrengthdesc) {
        this.strstrengthdesc = strstrengthdesc;
    }

    /**
     * @return the strleaderemail
     */
    public String getStrleaderemail() {
        return strleaderemail;
    }

    /**
     * @param strleaderemail
     *            the strleaderemail to set
     */
    public void setStrleaderemail(String strleaderemail) {
        this.strleaderemail = strleaderemail;
    }

    /**
     * @return the strleaderfax
     */
    public String getStrleaderfax() {
        return strleaderfax;
    }

    /**
     * @param strleaderfax
     *            the strleaderfax to set
     */
    public void setStrleaderfax(String strleaderfax) {
        this.strleaderfax = strleaderfax;
    }

    /**
     * @return the strleaderman
     */
    public String getStrleaderman() {
        return strleaderman;
    }

    /**
     * @param strleaderman
     *            the strleaderman to set
     */
    public void setStrleaderman(String strleaderman) {
        this.strleaderman = strleaderman;
    }

    /**
     * @return the strleadermobile
     */
    public String getStrleadermobile() {
        return strleadermobile;
    }

    /**
     * @param strleadermobile
     *            the strleadermobile to set
     */
    public void setStrleadermobile(String strleadermobile) {
        this.strleadermobile = strleadermobile;
    }

    /**
     * @return the strleaderprincipalship
     */
    public String getStrleaderprincipalship() {
        return strleaderprincipalship;
    }

    /**
     * @param strleaderprincipalship
     *            the strleaderprincipalship to set
     */
    public void setStrleaderprincipalship(String strleaderprincipalship) {
        this.strleaderprincipalship = strleaderprincipalship;
    }

    /**
     * @return the strpriserviceemail
     */
    public String getStrpriserviceemail() {
        return strpriserviceemail;
    }

    /**
     * @param strpriserviceemail
     *            the strpriserviceemail to set
     */
    public void setStrpriserviceemail(String strpriserviceemail) {
        this.strpriserviceemail = strpriserviceemail;
    }

    /**
     * @return the strpriservicefax
     */
    public String getStrpriservicefax() {
        return strpriservicefax;
    }

    /**
     * @param strpriservicefax
     *            the strpriservicefax to set
     */
    public void setStrpriservicefax(String strpriservicefax) {
        this.strpriservicefax = strpriservicefax;
    }

    /**
     * @return the strpriserviceman
     */
    public String getStrpriserviceman() {
        return strpriserviceman;
    }

    /**
     * @param strpriserviceman
     *            the strpriserviceman to set
     */
    public void setStrpriserviceman(String strpriserviceman) {
        this.strpriserviceman = strpriserviceman;
    }

    /**
     * @return the strpriservicemobile
     */
    public String getStrpriservicemobile() {
        return strpriservicemobile;
    }

    /**
     * @param strpriservicemobile
     *            the strpriservicemobile to set
     */
    public void setStrpriservicemobile(String strpriservicemobile) {
        this.strpriservicemobile = strpriservicemobile;
    }

    /**
     * @return the strpriserviceprincipalship
     */
    public String getStrpriserviceprincipalship() {
        return strpriserviceprincipalship;
    }

    /**
     * @param strpriserviceprincipalship
     *            the strpriserviceprincipalship to set
     */
    public void setStrpriserviceprincipalship(String strpriserviceprincipalship) {
        this.strpriserviceprincipalship = strpriserviceprincipalship;
    }

    /**
     * @return the strseclinkmanprincipalship
     */
    public String getStrseclinkmanprincipalship() {
        return strseclinkmanprincipalship;
    }

    /**
     * @param strseclinkmanprincipalship
     *            the strseclinkmanprincipalship to set
     */
    public void setStrseclinkmanprincipalship(String strseclinkmanprincipalship) {
        this.strseclinkmanprincipalship = strseclinkmanprincipalship;
    }

    /**
     * @return the strsecserviceemail
     */
    public String getStrsecserviceemail() {
        return strsecserviceemail;
    }

    /**
     * @param strsecserviceemail
     *            the strsecserviceemail to set
     */
    public void setStrsecserviceemail(String strsecserviceemail) {
        this.strsecserviceemail = strsecserviceemail;
    }

    /**
     * @return the strsecservicefax
     */
    public String getStrsecservicefax() {
        return strsecservicefax;
    }

    /**
     * @param strsecservicefax
     *            the strsecservicefax to set
     */
    public void setStrsecservicefax(String strsecservicefax) {
        this.strsecservicefax = strsecservicefax;
    }

    /**
     * @return the strsecserviceman
     */
    public String getStrsecserviceman() {
        return strsecserviceman;
    }

    /**
     * @param strsecserviceman
     *            the strsecserviceman to set
     */
    public void setStrsecserviceman(String strsecserviceman) {
        this.strsecserviceman = strsecserviceman;
    }

    /**
     * @return the strsecservicemobile
     */
    public String getStrsecservicemobile() {
        return strsecservicemobile;
    }

    /**
     * @param strsecservicemobile
     *            the strsecservicemobile to set
     */
    public void setStrsecservicemobile(String strsecservicemobile) {
        this.strsecservicemobile = strsecservicemobile;
    }

    /**
     * @return the strsecserviceprincipalship
     */
    public String getStrsecserviceprincipalship() {
        return strsecserviceprincipalship;
    }

    /**
     * @param strsecserviceprincipalship
     *            the strsecserviceprincipalship to set
     */
    public void setStrsecserviceprincipalship(String strsecserviceprincipalship) {
        this.strsecserviceprincipalship = strsecserviceprincipalship;
    }

    /**
     * @return the strsectechlinkman
     */
    public String getStrsectechlinkman() {
        return strsectechlinkman;
    }

    /**
     * @param strsectechlinkman
     *            the strsectechlinkman to set
     */
    public void setStrsectechlinkman(String strsectechlinkman) {
        this.strsectechlinkman = strsectechlinkman;
    }

    /**
     * @return the strsectechlinkmanemail
     */
    public String getStrsectechlinkmanemail() {
        return strsectechlinkmanemail;
    }

    /**
     * @param strsectechlinkmanemail
     *            the strsectechlinkmanemail to set
     */
    public void setStrsectechlinkmanemail(String strsectechlinkmanemail) {
        this.strsectechlinkmanemail = strsectechlinkmanemail;
    }

    /**
     * @return the strsectechlinkmanfax
     */
    public String getStrsectechlinkmanfax() {
        return strsectechlinkmanfax;
    }

    /**
     * @param strsectechlinkmanfax
     *            the strsectechlinkmanfax to set
     */
    public void setStrsectechlinkmanfax(String strsectechlinkmanfax) {
        this.strsectechlinkmanfax = strsectechlinkmanfax;
    }

    /**
     * @return the strsectechlinkmanmobile
     */
    public String getStrsectechlinkmanmobile() {
        return strsectechlinkmanmobile;
    }

    /**
     * @param strsectechlinkmanmobile
     *            the strsectechlinkmanmobile to set
     */
    public void setStrsectechlinkmanmobile(String strsectechlinkmanmobile) {
        this.strsectechlinkmanmobile = strsectechlinkmanmobile;
    }

    /**
     * @return the strserviceemail
     */
    public String getStrserviceemail() {
        return strserviceemail;
    }

    /**
     * @param strserviceemail
     *            the strserviceemail to set
     */
    public void setStrserviceemail(String strserviceemail) {
        this.strserviceemail = strserviceemail;
    }

    /**
     * @return the strservicefax
     */
    public String getStrservicefax() {
        return strservicefax;
    }

    /**
     * @param strservicefax
     *            the strservicefax to set
     */
    public void setStrservicefax(String strservicefax) {
        this.strservicefax = strservicefax;
    }

    /**
     * @return the strserviceman
     */
    public String getStrserviceman() {
        return strserviceman;
    }

    /**
     * @param strserviceman
     *            the strserviceman to set
     */
    public void setStrserviceman(String strserviceman) {
        this.strserviceman = strserviceman;
    }

    /**
     * @return the strservicemobile
     */
    public String getStrservicemobile() {
        return strservicemobile;
    }

    /**
     * @param strservicemobile
     *            the strservicemobile to set
     */
    public void setStrservicemobile(String strservicemobile) {
        this.strservicemobile = strservicemobile;
    }

    /**
     * @return the strspid
     */
    public String getStrspid() {
        return strspid;
    }

    /**
     * @param strspid
     *            the strspid to set
     */
    public void setStrspid(String strspid) {
        this.strspid = strspid;
    }

    /**
     * @return the strtechlinkman
     */
    public String getStrtechlinkman() {
        return strtechlinkman;
    }

    /**
     * @param strtechlinkman
     *            the strtechlinkman to set
     */
    public void setStrtechlinkman(String strtechlinkman) {
        this.strtechlinkman = strtechlinkman;
    }

    /**
     * @return the strtechlinkmanemail
     */
    public String getStrtechlinkmanemail() {
        return strtechlinkmanemail;
    }

    /**
     * @param strtechlinkmanemail
     *            the strtechlinkmanemail to set
     */
    public void setStrtechlinkmanemail(String strtechlinkmanemail) {
        this.strtechlinkmanemail = strtechlinkmanemail;
    }

    /**
     * @return the strtechlinkmanfax
     */
    public String getStrtechlinkmanfax() {
        return strtechlinkmanfax;
    }

    /**
     * @param strtechlinkmanfax
     *            the strtechlinkmanfax to set
     */
    public void setStrtechlinkmanfax(String strtechlinkmanfax) {
        this.strtechlinkmanfax = strtechlinkmanfax;
    }

    /**
     * @return the strtechlinkmanmobile
     */
    public String getStrtechlinkmanmobile() {
        return strtechlinkmanmobile;
    }

    /**
     * @param strtechlinkmanmobile
     *            the strtechlinkmanmobile to set
     */
    public void setStrtechlinkmanmobile(String strtechlinkmanmobile) {
        this.strtechlinkmanmobile = strtechlinkmanmobile;
    }

    /**
     * @return the strtechlinkmanprincipalship
     */
    public String getStrtechlinkmanprincipalship() {
        return strtechlinkmanprincipalship;
    }

    /**
     * @param strtechlinkmanprincipalship
     *            the strtechlinkmanprincipalship to set
     */
    public void setStrtechlinkmanprincipalship(String strtechlinkmanprincipalship) {
        this.strtechlinkmanprincipalship = strtechlinkmanprincipalship;
    }

    /**
     * @return the strindustry
     */
    public String getStrindustry() {
        return strindustry;
    }

    /**
     * @param strindustry
     *            the strindustry to set
     */
    public void setStrindustry(String strindustry) {
        this.strindustry = strindustry;
    }

    /**
     * @return the strsppaperid
     */
    public String getStrsppaperid() {
        return strsppaperid;
    }

    /**
     * @param strsppaperid
     *            the strsppaperid to set
     */
    public void setStrsppaperid(String strsppaperid) {
        this.strsppaperid = strsppaperid;
    }

    public String getStrspadminid() {
        return strspadminid;
    }

    public void setStrspadminid(String strspadminid) {
        this.strspadminid = strspadminid;
    }

    /**
     * @return the strprofilefir
     */
    public String getStrprofilefir() {
        return strprofilefir;
    }

    /**
     * @param strprofilefir
     *            the strprofilefir to set
     */
    public void setStrprofilefir(String strprofilefir) {
        this.strprofilefir = strprofilefir;
    }

    /**
     * @return the strprofilesec
     */
    public String getStrprofilesec() {
        return strprofilesec;
    }

    /**
     * @param strprofilesec
     *            the strprofilesec to set
     */
    public void setStrprofilesec(String strprofilesec) {
        this.strprofilesec = strprofilesec;
    }

    /**
     * @return the straccountname
     */
    public String getStraccountname() {
        return straccountname;
    }

    /**
     * @param straccountname
     *            the straccountname to set
     */
    public void setStraccountname(String straccountname) {
        this.straccountname = straccountname;
    }

    /**
     * @return the strbusinesscard
     */
    public String getStrbusinesscard() {
        return strbusinesscard;
    }

    /**
     * @param strbusinesscard
     *            the strbusinesscard to set
     */
    public void setStrbusinesscard(String strbusinesscard) {
        this.strbusinesscard = strbusinesscard;
    }

    /**
     * @return the strbusinessdate
     */
    public String getStrbusinessdate() {
        return strbusinessdate;
    }

    /**
     * @param strbusinessdate
     *            the strbusinessdate to set
     */
    public void setStrbusinessdate(String strbusinessdate) {
        this.strbusinessdate = strbusinessdate;
    }

    /**
     * @return the strdeputy
     */
    public String getStrdeputy() {
        return strdeputy;
    }

    /**
     * @param strdeputy
     *            the strdeputy to set
     */
    public void setStrdeputy(String strdeputy) {
        this.strdeputy = strdeputy;
    }

    /**
     * @return the strloginfund
     */
    public String getStrloginfund() {
        return strloginfund;
    }

    /**
     * @param strloginfund
     *            the strloginfund to set
     */
    public void setStrloginfund(String strloginfund) {
        this.strloginfund = strloginfund;
    }

    /**
     * @return the strlogintaxcard
     */
    public String getStrlogintaxcard() {
        return strlogintaxcard;
    }

    /**
     * @param strlogintaxcard
     *            the strlogintaxcard to set
     */
    public void setStrlogintaxcard(String strlogintaxcard) {
        this.strlogintaxcard = strlogintaxcard;
    }

    /**
     * @return the strpaltype
     */
    public String getStrpaltype() {
        return strpaltype;
    }

    /**
     * @param strpaltype
     *            the strpaltype to set
     */
    public void setStrpaltype(String strpaltype) {
        this.strpaltype = strpaltype;
    }

    /**
     * @return the intsiomsflag
     */
    public String getIntsiomsflag() {
        return intsiomsflag;
    }

    /**
     * @param intsiomsflag
     *            the intsiomsflag to set
     */
    public void setIntsiomsflag(String intsiomsflag) {
        this.intsiomsflag = intsiomsflag;
    }

    public String getStrsptype() {
        return strsptype;
    }

    public void setStrsptype(String strsptype) {
        this.strsptype = strsptype;
    }


}
