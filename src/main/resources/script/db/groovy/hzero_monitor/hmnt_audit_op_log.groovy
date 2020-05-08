package script.db.groovy.hzero_monitor

databaseChangeLog(logicalFilePath: 'script/script/hmnt_audit_op_log.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-07-24-hmnt_audit_op_log") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hmnt_audit_op_log_s', startValue:"1")
        }
        createTable(tableName: "hmnt_audit_op_log", remarks: "操作审计记录") {
            column(name: "log_id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
            column(name: "service_name", type: "varchar(" + 60 * weight + ")",  remarks: "操作审计数据来源服务")  {constraints(nullable:"false")}  
            column(name: "user_id", type: "bigint(20)",  remarks: "操作用户，iam_user.user_id")   
            column(name: "audit_content", type: "varchar(" + 480 * weight + ")",  remarks: "操作审计内容")  {constraints(nullable:"false")}  
            column(name: "audit_datetime", type: "datetime",  remarks: "操作审计发生时间")  {constraints(nullable:"false")}  
            column(name: "audit_result", type: "varchar(" + 30 * weight + ")",  remarks: "操作审计结果，值集HMNT.AUDIT_RESULT[SUCCESS(成功),FAILED(失败)]")   
            column(name: "time_consuming", type: "bigint(20)",  remarks: "操作耗时")   
            column(name: "tenant_id", type: "bigint(20)",  remarks: "租户ID,hpfm_tenant.tenant_id")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

    }

    changeSet(author: "hzero@hand-china.com", id: "2019-10-16-hmnt_audit_op_log-1") {
        addColumn(tableName: "hmnt_audit_op_log") {
            column(name: "request_url", type: "varchar(480)", remarks: "请求路径")
        }
    }

    changeSet(author: "hzero@hand-china.com", id: "2019-10-16-hmnt_audit_op_log-2") {
        addColumn(tableName: "hmnt_audit_op_log") {
            column(name: "request_ip", type: "varchar(30)", remarks: "请求IP")
        }
    }

    changeSet(author: "hzero@hand-china.com", id: "2019-10-16-hmnt_audit_op_log-3") {
        addColumn(tableName: "hmnt_audit_op_log") {
            column(name: "request_referrer", type: "varchar(480)", remarks: "请求referer")
        }
    }

    changeSet(author: "hzero@hand-china.com", id: "2019-10-16-hmnt_audit_op_log-4") {
        addColumn(tableName: "hmnt_audit_op_log") {
            column(name: "request_user_agent", type: "varchar(480)", remarks: "请求用户代理")
        }
    }

    changeSet(author: "hzero@hand-china.com", id: "2019-10-16-hmnt_audit_op_log-5") {
        addColumn(tableName: "hmnt_audit_op_log") {
            column(name: "request_method", type: "varchar(30)", remarks: "请求方式")
        }
    }

    changeSet(author: 'hzero@hand-china.com', id: '2020-01-04-hmnt_audit_op_log') {
        addColumn(tableName: 'hmnt_audit_op_log') {
            column(name: 'audit_batch_number', type: "varchar(60)", remarks: '审计批次号，hmnt_audit_data.audit_batch_number')
        }
    }
}