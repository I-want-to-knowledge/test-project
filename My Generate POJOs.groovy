import com.intellij.database.model.DasTable
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil
import com.intellij.database.model.DasTable
import com.intellij.database.model.ObjectKind
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil
import java.io.*
import java.text.SimpleDateFormat

/*
 * Available context bindings:
 *   SELECTION   Iterable<DasObject>
 *   PROJECT     project
 *   FILES       files helper
 */
packageName = ""
typeMapping = [
        (~/(?i)tinyint|smallint/)      : "Short",
        (~/(?i)mediumint|int/)         : "Integer",
        (~/(?i)bool|bit/)              : "Boolean",
        (~/(?i)float|double/)          : "Double",
        (~/(?i)real|decimal|numeric/)  : "BigDecimal",
        (~/(?i)timestamp/)             : "Timestamp",
        (~/(?i)datetime|date|time/)    : "Date",
        (~/(?i)binary|bfile|raw|image/): "InputStream",
        (~/(?i)blob|clob/)             : "String",
        (~/(?i)number/)                : "Long",
        (~/(?i)/)                      : "String"
]


FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
    SELECTION.filter { it instanceof DasTable && it.getKind() == ObjectKind.TABLE }.each { generate(it, dir) }
}

def generate(table, dir) {
    //def className = javaClassName(table.getName(), true)
    def className = javaName(table.getName(), true)
    className = className + "Do"// 重命名
    def fields = calcFields(table)
    packageName = getPackageName(dir)
    PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(new File(dir as String, className + ".java")), "UTF-8"))
    printWriter.withPrintWriter { out -> generate(out, className, fields, table) }

//    new File(dir, className + ".java").withPrintWriter { out -> generate(out, className, fields,table) }
}

// 获取包所在文件夹路径
def getPackageName(dir) {
    return dir.toString().replaceAll("\\\\", ".").replaceAll("/", ".").replaceAll("^.*src(\\.main\\.java\\.)?", "") + ";"
}

def generate(out, className, fields, table) {
    def tableName = table.getName()
    out.println "package $packageName"
    out.println ""
//  out.println "import javax.persistence.Column;"
//  out.println "import javax.persistence.Entity;"
//  out.println "import javax.persistence.Table;"
    out.println "import java.io.Serializable;"
//  out.println "import com.fasterxml.jackson.annotation.JsonIgnoreProperties;"
//  out.println "import com.fasterxml.jackson.annotation.JsonInclude;"
//  out.println "import lombok.NoArgsConstructor;"
//  out.println "import lombok.EqualsAndHashCode;"
//  out.println "import lombok.Getter;"
//  out.println "import lombok.Setter;"
//  out.println "import lombok.ToString;"
    out.println "import lombok.Data;"

    Set types = new HashSet()

    fields.each() {
        types.add(it.type)
    }

    if (types.contains("Date")) {
        out.println "import java.util.Date;"
    }

    if (types.contains("BigDecimal")) {
        out.println "import java.math.BigDecimal;"
    }

    if (types.contains("InputStream")) {
        out.println "import java.io.InputStream;"
    }
    out.println ""
    out.println "/**\n" +
//          " * @Description  \n" +
            " * " + tableName + "\u8868\n" +
            " * @author YanZhen\n" +
            " * @date " + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()) + " \n" +
            " */"
//  out.println ""
    out.println "@Data"
//  out.println "@Getter"
//  out.println "@Setter"
//  out.println "@ToString"
//  out.println "@NoArgsConstructor"
//  out.println "@EqualsAndHashCode"
//  out.println "@JsonInclude(JsonInclude.Include.NON_NULL)"
//  out.println "@JsonIgnoreProperties(value = {\"hibernateLazyInitializer\", \"handler\", \"fieldHandler\"}, ignoreUnknown = true)"
//  out.println "@Entity"
//  out.println "@Table ( name =\""+table.getName() +"\" )"
    out.println "public class $className implements Serializable {"
    out.println genSerialID()


    // 判断自增
    if ((tableName + "_id").equalsIgnoreCase(fields[0].colum) || "id".equalsIgnoreCase(fields[0].colum)) {
        out.println "    @Id"
    }


    fields.each() {
        out.println ""
        // 输出注释
        if (isNotEmpty(it.commoent)) {
            out.println "    /**"
            out.println "     * ${it.commoent.toString()}"
            out.println "     */"
        }

        // if (it.annos != "") out.println "   ${it.annos.replace("[@Id]", "")}"

        // 输出成员变量
        out.println "    private ${it.type} ${it.name};"

        // 测试使用
//        out.println "// Data put : " + it.toString()
    }

    // 输出get/set方法
//    fields.each() {
//        out.println ""
//        out.println "    public ${it.type} get${it.name.capitalize()}() {"
//        out.println "        return this.${it.name};"
//        out.println "    }"
//        out.println ""
//
//        out.println "    public void set${it.name.capitalize()}(${it.type} ${it.name}) {"
//        out.println "        this.${it.name} = ${it.name};"
//        out.println "    }"
//    }
    out.println ""
    out.println "}"
}

def calcFields(table) {
    DasUtil.getColumns(table).reduce([]) { fields, col ->
        // 获取数据库字段类型，并转换为小写
        def spec = Case.LOWER.apply(col.getDataType().getSpecification())
        // 获取字段对应的Java类型
        def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value
        // 针对long类型的字段，再进行细分
        if (typeStr != null && typeStr == "Long") {
            // 整数位长度
            def integerPlaces = col.getDataType().length
            // 有小数位的，默认BigDecimal类型
            if (col.getDataType().scale > 0) {
                typeStr = "BigDecimal"
            } else {
                // 否则，区分Java的整型
                // Long
                if (integerPlaces > 9) {
//                    typeStr = ""
                } else if (integerPlaces > 4) {
                    typeStr = "Integer"
                } else if (integerPlaces > 2) {
                    typeStr = "Short"
                } else if (integerPlaces > 0) {
                    typeStr = "Byte"
                }
            }
        }

        // 获取字段信息
        def comm = [
                colName : col.getName(), // 字段名
                name    : javaName(col.getName(), false), // 类名
                type    : typeStr, // 字段类型
                commoent: col.getComment(), // 字段注释
                test : spec,
//                text  : col.getText(),
//                manager  : col.getManager(),
                // annos: "    @Column(name = \""+col.getName()+"\" )"
                annos   : ""]
//        if ("id".equals(Case.LOWER.apply(col.getName())))
//            comm.annos += ["@Id"]
        fields += [comm]
    }
}

// 处理类名（这里是因为我的表都是以t_命名的，所以需要处理去掉生成类名时的开头的T，
// 如果你不需要那么请查找用到了 javaClassName这个方法的地方修改为 javaName 即可）
def javaClassName(str, capitalize) {
    def s = com.intellij.psi.codeStyle.NameUtil.splitNameIntoWords(str)
            .collect { Case.LOWER.apply(it).capitalize() }
            .join("")
            .replaceAll(/[^\p{javaJavaIdentifierPart}[_]]/, "_")
    // 去除开头的T  http://developer.51cto.com/art/200906/129168.htm
    s = s[1..s.size() - 1]
    capitalize || s.length() == 1 ? s : Case.LOWER.apply(s[0]) + s[1..-1]
}

def javaName(str, capitalize) {
//    def s = str.split(/(?<=[^\p{IsLetter}])/).collect { Case.LOWER.apply(it).capitalize() }
//            .join("").replaceAll(/[^\p{javaJavaIdentifierPart}]/, "_")
//    capitalize || s.length() == 1? s : Case.LOWER.apply(s[0]) + s[1..-1]
    def s = com.intellij.psi.codeStyle.NameUtil.splitNameIntoWords(str)
            .collect { Case.LOWER.apply(it).capitalize() }
            .join("")
            .replaceAll(/[^\p{javaJavaIdentifierPart}[_]]/, "_")
    capitalize || s.length() == 1 ? s : Case.LOWER.apply(s[0]) + s[1..-1]
}

def isNotEmpty(content) {
    return content != null && content.toString().trim().length() > 0
}

static String changeStyle(String str, boolean toCamel) {
    if (!str || str.size() <= 1)
        return str

    if (toCamel) {
        String r = str.toLowerCase().split('_').collect { cc -> Case.LOWER.apply(cc).capitalize() }.join('')
        return r[0].toLowerCase() + r[1..-1]
    } else {
        str = str[0].toLowerCase() + str[1..-1]
        return str.collect { cc -> ((char) cc).isUpperCase() ? '_' + cc.toLowerCase() : cc }.join('')
    }
}

static String genSerialID() {
    return "    private static final long serialVersionUID =  " + Math.abs(new Random().nextLong()) + "L;"
}
