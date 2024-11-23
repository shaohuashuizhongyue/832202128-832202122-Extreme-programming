package com.controller.admin;

import com.entity.Liaison;
import com.dto.Constants;
import com.dto.ResponseDto;
import com.github.pagehelper.PageHelper;
import com.service.LiaisonService;
import com.dto.PageDto;
import com.dto.LiaisonDto;
import com.util.Info;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.tomcat.util.bcel.classfile.ElementValue;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import javax.annotation.Resource;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.sql.Types;
import java.util.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import java.util.List;
import java.util.logging.Logger;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/admin/liaison/")
public class LiaisonController {
    @Resource
    private LiaisonService liaisonService;
    private static final Logger logger = Logger.getLogger(LiaisonController.class.getName());

    public LiaisonController(LiaisonService liaisonService) {
        this.liaisonService = liaisonService;
    }

    public static final String TABLE_NAME = "联系人";

    /**
     * ====后台联系人管理====
     *
     * @param liaison
     * @return
     */

    /*保存*/
    @RequestMapping("save")
    public String save(@RequestBody Liaison liaison) {
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("id=="+liaison.getId());
        if (liaison.getId()==null){
        Timestamp time = new Timestamp(System.currentTimeMillis());
        liaison.setPubtime(time.toString().substring(0,19));
        liaisonService.save(liaison);
        }else {
        liaisonService.updateById(liaison);
        }
        return "success";
    }

    /*分页列表*/
    @RequestMapping("list")
   public LiaisonDto list(LiaisonDto liaisonDto) {
        Map<String, Object> bMap = new HashMap<String, Object>();
        QueryWrapper<Liaison> wrapper = new QueryWrapper<>();
        if (liaisonDto.getName() != null && !liaisonDto.getName().equals("")) {
            wrapper.like("name",liaisonDto.getName());
        }
        if (liaisonDto.getSex() != null && !liaisonDto.getSex().equals("")) {
            wrapper.like("sex",liaisonDto.getSex());
        }
        if (liaisonDto.getAge() != null && !liaisonDto.getAge().equals("")) {
            wrapper.like("age",liaisonDto.getAge());
        }
        if (liaisonDto.getUid() != null && !liaisonDto.getUid().equals("")) {
            wrapper.like("uid",liaisonDto.getUid());
        }
        if (liaisonDto.getTel() != null && !liaisonDto.getTel().equals("")) {
            wrapper.like("tel",liaisonDto.getTel());
        }
        if (liaisonDto.getEmail() != null && !liaisonDto.getEmail().equals("")) {
            wrapper.like("email",liaisonDto.getEmail());
        }
        if (liaisonDto.getMaccount() != null && !liaisonDto.getMaccount().equals("")) {
            wrapper.like("maccount",liaisonDto.getMaccount());
        }
        if (liaisonDto.getAddress() != null && !liaisonDto.getAddress().equals("")) {
            wrapper.like("address",liaisonDto.getAddress());
        }
        wrapper.orderByDesc("id");
        List<Liaison> list = liaisonService.list(wrapper);
        liaisonDto.setTotal(list.size());
		PageHelper.startPage(liaisonDto.getPage(),liaisonDto.getSize());
		list = liaisonService.list(wrapper);
        liaisonDto.setList(list);
		return liaisonDto;
    }

    /*联系人搜索列表*/
	@RequestMapping("search")
	public LiaisonDto search(LiaisonDto liaisonDto) {
		Map<String, Object> bMap = new HashMap<String, Object>();
        QueryWrapper<Liaison> wrapper = new QueryWrapper<>();
        if (liaisonDto.getName() != null && !liaisonDto.getName().equals("")) {
            wrapper.like("name",liaisonDto.getName());
        }
        if (liaisonDto.getSex() != null && !liaisonDto.getSex().equals("")) {
            wrapper.like("sex",liaisonDto.getSex());
        }
        if (liaisonDto.getAge() != null && !liaisonDto.getAge().equals("")) {
            wrapper.like("age",liaisonDto.getAge());
        }
        if (liaisonDto.getUid() != null && !liaisonDto.getUid().equals("")) {
            wrapper.like("uid",liaisonDto.getUid());
        }
        if (liaisonDto.getTel() != null && !liaisonDto.getTel().equals("")) {
            wrapper.like("tel",liaisonDto.getTel());
        }
        if (liaisonDto.getEmail() != null && !liaisonDto.getEmail().equals("")) {
            wrapper.like("email",liaisonDto.getEmail());
        }
        if (liaisonDto.getMaccount() != null && !liaisonDto.getMaccount().equals("")) {
            wrapper.like("maccount",liaisonDto.getMaccount());
        }
        if (liaisonDto.getAddress() != null && !liaisonDto.getAddress().equals("")) {
            wrapper.like("address",liaisonDto.getAddress());
        }
        wrapper.orderByDesc("id");
        List<Liaison> list = liaisonService.list(wrapper);
        liaisonDto.setTotal(list.size());
		PageHelper.startPage(liaisonDto.getPage(),liaisonDto.getSize());
		list = liaisonService.list(wrapper);
        liaisonDto.setList(list);
		return liaisonDto;
	}

    /*删除*/
    @RequestMapping("delete/{id}")
    public String delete(@PathVariable Integer id) {
        liaisonService.removeById(id);
        return "success";
    }

    /*获取单个对象*/
    @RequestMapping("getById/{id}")
    public Liaison getById(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        Liaison liaison1 = liaisonService.getById(id);
        return liaison1;
    }

    /*获取所有*/
    @RequestMapping("all")
    public List<Liaison> all() {
        Map<String, Object> bMap = new HashMap<String, Object>();
        QueryWrapper<Liaison> wrapper = new QueryWrapper<>();
        wrapper.allEq(bMap);
        List<Liaison> list = liaisonService.list(wrapper);
        return list;
    }

    /**
     * ===导入===
     *
     * @param file
     * @return
     */

    @RequestMapping("upload")
    public ResponseEntity<String> uploadFile1(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("文件不能为空");
        }

        // 使用 try-with-resources 自动管理资源
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);  // 读取第一个 sheet
            Iterator<Row> rowIterator = sheet.iterator();
            List<Liaison> liaisonList = new ArrayList<>();

            // 跳过表头
            if (rowIterator.hasNext()) rowIterator.next();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Liaison liaison = parseRowToLiaison(row);
                //liaisonService.save(liaison);
                if (liaison != null) {
                    liaisonList.add(liaison);
                }
            }

            // 批量保存数据
            if (!liaisonList.isEmpty()) {
                liaisonService.saveBatch(liaisonList);
            }
            return ResponseEntity.ok("文件上传并解析成功！");
        } catch (IOException e) {
            logger.severe("文件解析失败：" + e.getMessage());
            return ResponseEntity.status(500).body("文件解析失败");
        }
    }

    /**
     * 将 Excel 行解析为 Liaison 对象
     *
     * @param row Excel 的一行数据
     * @return Liaison 对象
     */
    private Liaison parseRowToLiaison(Row row) {
        try {
            Liaison liaison = new Liaison();
            // 假设各列的顺序：姓名，性别，年龄，用户id，电话，邮箱，媒体账号，地址，备注
            String name = getCellValue(row.getCell(1));
            String sex = getCellValue(row.getCell(2));
            String age = getCellValue(row.getCell(3));
            String uid = getCellValue(row.getCell(4));
            String tel = getCellValue(row.getCell(5));
            String email = getCellValue(row.getCell(6));
            String maccount = getCellValue(row.getCell(7));
            String address = getCellValue(row.getCell(8));
            String note = getCellValue(row.getCell(9));

            if (name == null || uid == null) {
                // 如果必填字段为空，跳过该行
                return null;
            }

            Timestamp time = new Timestamp(System.currentTimeMillis());

            // 设置 Liaison 对象的字段
            liaison.setName(name);
            liaison.setSex(sex);
            liaison.setAge(age);
            liaison.setUid(uid);  // 注意处理类型转换异常
            liaison.setTel(tel);
            liaison.setEmail(email);
            liaison.setMaccount(maccount);
            liaison.setAddress(address);
            liaison.setNote(note);
            liaison.setPubtime(time.toString().substring(0, 19));

            return liaison;
        } catch (Exception e) {
            logger.warning("解析行数据时发生错误: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从 Excel 单元格获取值
     *
     * @param cell 单元格
     * @return 单元格值
     */
    private String getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return null;
        }
    }

    @RequestMapping("upload1")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("文件不能为空");
        }

        try (InputStream inputStream = file.getInputStream();
             ) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);  // 读取第一个 sheet

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Liaison liaison = new Liaison();
                /*HSSFCell name = row.getCell(0); // 姓名
                HSSFCell sex = row.getCell(1); // 性别
                HSSFCell age = row.getCell(2); // 年龄
                HSSFCell uid = row.getCell(3); // 用户id
                HSSFCell tel = row.getCell(4); // 电话
                HSSFCell email = row.getCell(5); // 邮箱
                HSSFCell maccount = row.getCell(6); // 媒体账号
                HSSFCell address = row.getCell(7); // 地址
                HSSFCell note = row.getCell(8); // 备注*/
                // 假设第一列是姓名，第二列是性别
                String name = row.getCell(0).getStringCellValue();
                String sex = row.getCell(1).getStringCellValue();
                String age = row.getCell(2).getStringCellValue();
                String uid = row.getCell(3).getStringCellValue();
                String tel = row.getCell(4).getStringCellValue();
                String email = row.getCell(5).getStringCellValue();
                String maccount = row.getCell(6).getStringCellValue();
                String address = row.getCell(7).getStringCellValue();
                String note = row.getCell(8).getStringCellValue();

                // 可以在这里处理每行数据，例如存入数据库
                System.out.println("姓名: " + name + ", 年龄: " + age);
                Timestamp time = new Timestamp(System.currentTimeMillis());
                liaison.setName(name);
                liaison.setSex(sex);
                liaison.setAge(age);
                liaison.setUid(uid);
                liaison.setTel(tel);
                liaison.setEmail(email);
                liaison.setMaccount(maccount);
                liaison.setAddress(address);
                liaison.setNote(note);
                liaison.setPubtime(time.toString().substring(0, 19));

                liaisonService.save(liaison);
            }
            //workbook.close();
            inputStream.close();


            return ResponseEntity.ok("文件上传并解析成功！");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("文件解析失败");
        }
    }




@RequestMapping("imp")
public void imp(@RequestParam("file") MultipartFile file, HttpServletResponse response, HttpSession session) throws IOException {
    Map<String, Object> result = new HashMap<>();
    response.setContentType("application/json;charset=utf-8");
    try (InputStream is = file.getInputStream()) {
        if (!file.isEmpty() && file.getOriginalFilename().endsWith("xls")) {
            HSSFWorkbook hssf = new HSSFWorkbook(is);
            HSSFSheet sheet = hssf.getSheet("Sheet1");
            Liaison user = new Liaison();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                HSSFRow row = sheet.getRow(i);
                if (row == null) continue;

                HSSFCell name = row.getCell(0); // 姓名
                HSSFCell sex = row.getCell(1); // 性别
                HSSFCell age = row.getCell(2); // 年龄
                HSSFCell uid = row.getCell(3); // 用户id
                HSSFCell tel = row.getCell(4); // 电话
                HSSFCell email = row.getCell(5); // 邮箱
                HSSFCell maccount = row.getCell(6); // 媒体账号
                HSSFCell address = row.getCell(7); // 地址
                HSSFCell note = row.getCell(8); // 备注

                if (name == null || sex == null || age == null || uid == null || tel == null || email == null || maccount == null || address == null || note == null) {
                    result.put("error", "第" + (i + 1) + "行数据不完整");
                    break;
                }

                Timestamp time = new Timestamp(System.currentTimeMillis());
                user.setName(name.toString());
                user.setSex(sex.toString());
                user.setAge(age.toString());
                user.setUid(uid.toString());
                user.setTel(tel.toString());
                user.setEmail(email.toString());
                user.setMaccount(maccount.toString());
                user.setAddress(address.toString());
                user.setNote(note.toString());
                user.setPubtime(time.toString().substring(0, 19));

                liaisonService.save(user);
            }

            result.put("info", "ok");
        } else {
            result.put("error", "文件为空或不是 .xls 文件");
        }
    } catch (Exception e) {
        result.put("error", "处理文件时发生错误: " + e.getMessage());
    }

    try (PrintWriter out = response.getWriter()) {
        out.print(new JSONObject(result));
        out.flush();
    }
}

    /**
     * ===导出===
     *
     * @return
     */

    @RequestMapping("export")
    public String export(HttpServletResponse response, LiaisonDto liaisonDto) throws Exception {
        QueryWrapper<Liaison> wrapper = new QueryWrapper<>();
        String uid = liaisonDto.getUid();
        if (uid != null) {
            wrapper.eq("uid", uid);
        }
        wrapper.orderByDesc("id");

        List<Liaison> list = liaisonService.list(wrapper);
        String[] tableHeader = { "序号", "名称", "性别", "电话号码", "电子邮件地址", "社交媒体账号", "物理地址", "备注", "发布时间" };

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("联系人信息表");
        HSSFHeader header = sheet.getHeader();

        try (OutputStream out = response.getOutputStream()) {
            if (list.isEmpty()) {
                header.setCenter("查无数据");
            } else {
                header.setCenter("联系人信息表");
                createHeaderRow(sheet, tableHeader);
                fillDataRows(sheet, list);
            }

            setResponseHeaders(response);

            workbook.write(out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "success";
    }

    private void createHeaderRow(HSSFSheet sheet, String[] tableHeader) {
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) 400);

        CellStyle style = createCellStyle(sheet);
        for (int k = 0; k < tableHeader.length; k++) {
            HSSFCell cell = row.createCell(k);
            cell.setCellValue(tableHeader[k]);
            sheet.setColumnWidth(k, 8000);  // Set column width
            cell.setCellStyle(style);
        }
    }

    private void fillDataRows(HSSFSheet sheet, List<Liaison> list) {
        CellStyle style = createCellStyle(sheet);

        for (int i = 0; i < list.size(); i++) {
            Liaison sign = list.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.setHeight((short) 400);

            setCellValue(row.createCell(0), i + 1, style);
            setCellValue(row.createCell(1), sign.getName(), style);
            setCellValue(row.createCell(2), sign.getSex(), style);
            setCellValue(row.createCell(3), sign.getTel(), style);
            setCellValue(row.createCell(4), sign.getEmail(), style);
            setCellValue(row.createCell(5), sign.getMaccount(), style);
            setCellValue(row.createCell(6), sign.getAddress(), style);
            setCellValue(row.createCell(7), sign.getNote(), style);
            setCellValue(row.createCell(8), sign.getPubtime(), style);
        }
    }

    private void setCellValue(HSSFCell cell, Object value, CellStyle style) {
        if (value != null) {
            if (value instanceof String) {
                cell.setCellValue((String) value);
            } else if (value instanceof Integer) {
                cell.setCellValue((Integer) value);
            } else if (value instanceof Double) {
                cell.setCellValue((Double) value);
            } else {
                cell.setCellValue(value.toString());
            }
        } else {
            cell.setCellValue("");
        }
        cell.setCellStyle(style);
    }

    private CellStyle createCellStyle(HSSFSheet sheet) {
        CellStyle style = sheet.getWorkbook().createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        HSSFFont font = sheet.getWorkbook().createFont();
        font.setColor(HSSFFont.COLOR_NORMAL);
        font.setFontHeightInPoints((short) 10);  // Set font height
        style.setFont(font);

        return style;
    }

    private void setResponseHeaders(HttpServletResponse response) {
        response.setHeader("Content-disposition", "attachment; filename=liaison2024" + Info.get20ID() + ".xls");
        response.setContentType("application/msexcel;charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    }




}
