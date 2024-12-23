//package com.gthm.itext.table;
//
////import com.itextpdf.io.font.constants.StandardFonts;
////import com.itextpdf.kernel.colors.ColorConstants;
////import com.itextpdf.kernel.events.Event;
////import com.itextpdf.kernel.events.IEventHandler;
////import com.itextpdf.kernel.events.PdfDocumentEvent;
////import com.itextpdf.kernel.font.PdfFont;
////import com.itextpdf.kernel.font.PdfFontFactory;
////import com.itextpdf.kernel.geom.PageSize;
////import com.itextpdf.kernel.geom.Rectangle;
////import com.itextpdf.kernel.pdf.PdfDocument;
////import com.itextpdf.kernel.pdf.PdfPage;
////import com.itextpdf.kernel.pdf.PdfReader;
////import com.itextpdf.kernel.pdf.PdfWriter;
////import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
////import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
////import com.itextpdf.layout.Canvas;
////import com.itextpdf.layout.Document;
////import com.itextpdf.layout.borders.Border;
////import com.itextpdf.layout.element.Cell;
////import com.itextpdf.layout.element.LineSeparator;
////import com.itextpdf.layout.element.Paragraph;
////import com.itextpdf.layout.element.Table;
////import com.itextpdf.layout.properties.TextAlignment;
////import com.itextpdf.layout.properties.UnitValue;
////import com.itextpdf.layout.properties.VerticalAlignment;
//
//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.PdfDocument;
//import com.itextpdf.text.pdf.PdfWriter;
//
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//public class MyOwnPDFBorderLess {
//
//    static String directory = "C:\\Users\\anon\\Pictures\\output-invoide\\";
//    private static String PATH = directory + "my-own.pdf";
//
//    private static String EMPTY_BLOCK_TXT = """
//
//        """;
//    private static String TAB = "\t";
//
//    static Font FONT_SIZE_9 = new Font(Font.FontFamily.HELVETICA, 9);
//    static Font FONT_SIZE_8 = new Font(Font.FontFamily.HELVETICA, 8);
//
//    public static void main(String[] args) {
//
//        try {
//
//            Document document = new Document();
//            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PATH));
//            PdfDocument pdfDoc = new PdfDocument();
//            pdfDoc.addWriter(writer);
//
//            document.setMargins(36, 20, 36, 20);
//            pdfDoc.setPageSize(PageSize.A4.rotate());
////            pdfDoc.setDefaultPageSize(PageSize.A4.rotate());
//
//            HeaderEventHandler headerEventHandler = new HeaderEventHandler("WALMART INC",
//                    "355670", "10/06/2024");
////            pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, headerEventHandler);
//
//
////             starting to construct the PDF
//            generateAddress(document);
//            generatePackingList(pdfDoc, document);
//            generateOtherInformation(document);
//            Table invoiceTable = generateInvoiceTableHeader(document);
//            generateTheDataInInvoiceTable(document, invoiceTable);
//
//
////            generate total in words
//
//            Paragraph totalAmountInWords =
//                    new Paragraph("INVOICE TOTAL VALUE IN US DOLLARS FORTY-NINE THOUSAND THREE HUNDRED " +
//                            "AND FIFTY TWO AND CENTS THIRTEEN");
//            totalAmountInWords.setSpacingBefore(15);
//            totalAmountInWords.setFont(FONT_SIZE_9);
//            totalAmountInWords.setIndentationLeft(30);
//            totalAmountInWords.setAlignment(Element.ALIGN_LEFT);
//            totalAmountInWords.setSpacingAfter(20);
//            document.add(totalAmountInWords);
//
////            generateUnder line and extra Stuff
//            generateUnderLineAndExtraStuff(document);
//
//
//
//            // close the document
//            document.close();
//
////             start generating the page number
//            String outputFilePath =  directory + File.separator + "my-own-page-num.pdf";
//            updatePageHeaders(headerEventHandler, PATH, outputFilePath);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//    private static void generateUnderLineAndExtraStuff(Document document) throws DocumentException {
//        addUnderLine(document);
//
//        Paragraph descriptionPara = new
//                Paragraph("DESCRIPTION, ORIGIN, QUANTITY, COST OF ANY FREE SUPPLIED PART(S):");
//        descriptionPara.setFont(FONT_SIZE_8);
//        descriptionPara.setAlignment(Element.ALIGN_LEFT);
//        descriptionPara.setIndentationLeft(30);
//
//        document.add(descriptionPara);
//
//        addUnderLine(document);
//
//
////         signature
//        Paragraph namePara = new Paragraph("JANE");
//                namePara.setFont(FONT_SIZE_8);
////                .setFontSize(8)
//        namePara.setAlignment(Element.ALIGN_LEFT);
//        namePara.setSpacingBefore(20);
//        namePara.setIndentationLeft(30);
////        .setTextAlignment(TextAlignment.LEFT).setMarginTop(20)
////                .setMarginLeft(30);
//
//        document.add(namePara);
//
//        Paragraph shippingSpecialist = new Paragraph("SHIPPING SPECIALIST").setFontSize(8)
//                                                  .setTextAlignment(TextAlignment.LEFT)
//                .setMarginLeft(30);
//
//        document.add(shippingSpecialist);
//
//
//    }
//
//    private static void generateInvoiceTotalRow(Table invoiceTable) {
//
//        String invoiceTotal = "INVOICE TOTAL ";
//
////        7,10,13
//        createRowForInvoiceTotal("", invoiceTotal, "1", "14", "42",
//                "588",  "83.9322", "49352.13",
//                "210.0000", "8820.0000",  "10010.7000", invoiceTable);
//
//
//
//    }
//
//    private static void generateTheDataInInvoiceTable(Document document, Table invoiceTable) {
//
//        //         generate shippingMarks
//        String shippingMarks = "To US Case ID\nPO # 2923267225\nPo Type # 43\nDept # 9\nItem # 595469863\nSupplier STK # BF-PC";
//        generateShippingMarksRow(shippingMarks , invoiceTable);
//
////         generate values for ASSORTMENT ITEMS
//        generateAssortmentItems(invoiceTable);
//
////        generate product description
//         generateProductDescription(invoiceTable);
//
////          generate item row
//        generateItem(invoiceTable);
//
//        //   generate invoice Total Row
//        generateInvoiceTotalRow(invoiceTable);
//
////         add invoice table to the document at last of adding all RoWS
//        document.add(invoiceTable);
//
//    }
//
//    private static void generateItem(Table invoiceTable) {
//
//        StringBuilder builder = new StringBuilder();
//        builder.append("ITEM # ").append("980272065").append(System.lineSeparator());
//
//
////        shippingMarks , assortment , WHSE pack ,VNDR pack, total VNDR packs,
////        total unit, weight, pack price,amount in USD, VNDR PACK type,
////        net vndr pack, net total, gross vndr pack, gross total
//
//        createRowForItemInInvoiceTable(EMPTY_BLOCK_TXT, builder.toString(),  "14",
//                "42", "588",  "83.9322", "49352.1336",
//                 "210.0000", "8820.0000",
//                 invoiceTable);
//
//        builder.setLength(0);
//        builder.append("ITEM DESCRIPTION: ").append("15PC HARD ANODIZED COOKEWARE SET").append(System.lineSeparator());
//        builder.append(TAB).append("  COMPONENT DETAILS: ").append(System.lineSeparator());
//
//        createExpandingRowForInvoiceTable(EMPTY_BLOCK_TXT, builder.toString(), EMPTY_BLOCK_TXT,
//                EMPTY_BLOCK_TXT,  EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,
//                EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,  invoiceTable);
//
////         clear builder to reuse
//        builder.setLength(0);
//
////        build the Common Name
//        for (int i = 0; i < 5; i++) {
//
//            builder.append(TAB).append("   COMMON NAME: ").append("2QT COVERED SAUCE PAN").append(System.lineSeparator());
//            builder.append(TAB).append(TAB).append("    BREAKDOWN:").append(System.lineSeparator());
//            builder.append(TAB).append(TAB).append("    STAINLESS STEEL - ").append("20%").append(System.lineSeparator());
//            builder.append(TAB).append(TAB).append("    GLASS - ").append("25%").append(System.lineSeparator());
//            builder.append(TAB).append(TAB).append("    ALUMINUM - ").append("55%").append(System.lineSeparator());
//            builder.append(TAB).append("   VALUE: ").append("7.8391").append(System.lineSeparator())
//                   .append(System.lineSeparator()).append(System.lineSeparator());
//
//            //     5 , 10, 12 ,13 , 14
//            createExpandingRowForInvoiceTable(EMPTY_BLOCK_TXT, builder.toString(), EMPTY_BLOCK_TXT,
//                    EMPTY_BLOCK_TXT,  EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,
//                    EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,  EMPTY_BLOCK_TXT,
//                    invoiceTable);
//
//        }
//
//        builder.setLength(0);
//        builder.append("ASSEMBLY MANUFACTURER:").append(System.lineSeparator());
//        builder.append("NAME: ").append("GUANGDONG MASTER GROUP CO., LTD").append(System.lineSeparator());
//        builder.append("ADDRESS: ")
//               .append("NO. 48-50 SOUTH SECTION, DANAN ROAD, XINXING COUNTY, YUNFU, GD, CN, 527300, 8618023382230")
//               .append(System.lineSeparator())
//               .append(System.lineSeparator())
//               .append(System.lineSeparator());
//
//
//        createExpandingRowForInvoiceTable(EMPTY_BLOCK_TXT, builder.toString(), EMPTY_BLOCK_TXT,
//                EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,
//                EMPTY_BLOCK_TXT,  EMPTY_BLOCK_TXT,  invoiceTable);
//
//    }
//
//    private static void generateProductDescription(Table invoiceTable) {
//        StringBuilder builder = new StringBuilder();
//        builder.append("PRODUCT DESCRIPTION: ").append("15PC HARD ANODIZED COOKEWARE SET").append(System.lineSeparator());
//        builder.append("ADDITIONAL PRODUCT DESCRIPTION: ").append(System.lineSeparator()).append(System.lineSeparator());
//        builder.append("VENDOR STK # ").append("15PCHA").append(System.lineSeparator());
//
//
////        createRowForIncomeTable(DUMMY_TEXT, builder.toString(), DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT,
////                DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT,
////                DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, invoiceTable);
//
////     5 , 10, 12 ,13 , 14
//        createExpandingRowForInvoiceTable(EMPTY_BLOCK_TXT, builder.toString(), EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,
//                 EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,
//                 EMPTY_BLOCK_TXT, invoiceTable);
//
//    }
//
//
//    private static void createExpandingRowForInvoiceTable(String shippingMarks, String assortment, String whsepack,
//                                                          String vndrPack,  String totalUnit,
//                                                          String weight, String packPrice, String amountInUSD,
//                                                           String netVNDRPack, Table invoiceTable
//    ) {
//
//        Cell[] row = new Cell[14];
//        row[0] = addCellForInvoice(2,3 , shippingMarks); // shipping marks
//        row[1] = addCellForInvoice(2,9 , assortment); // assortment
//        row[2] = addCellForInvoice(2,1 , whsepack); // WHSE pack
//        row[3] = addCellForInvoice(2,1 , vndrPack); // VNDR pack
//        row[4] = addCellForInvoice(2,1 , totalUnit); // total unit
//        row[5] = addCellForInvoice(1,0 , weight); // weight
//        row[6] = addCellForInvoice(2,1 , packPrice); // pack price
//        row[7] = addCellForInvoice(2,2 , amountInUSD); // amount in USD
//        row[8] = addCellForInvoice(1,1, netVNDRPack); // net vndr pack
//
//        for(Cell cell: row) {
//            if(cell != null) {
//                invoiceTable.addCell(cell);
//            }
//        }
//    }
//
////    7,
//    private static void createRowForAssortmentInvoiceTable(String shippingMarks, String assortment, String whsepack,
//                                                           String vndrPack, String totalVNDRPack, String totalUnit,
//                                                           String packPrice, String amountInUSD,
//                                                           String vndrPackType, String netVNDRPack, String netTotal,
//                                                           String grossVndrPack, String grossTotal, Table invoiceTable
//    ) {
//
//        Cell[] row = new Cell[14];
//        row[0] = addCellForInvoice(2,3 , shippingMarks); // shipping marks
//        row[1] = addCellForInvoice(2,6 , assortment); // assortment
//        row[2] = addCellForInvoice(2,1 , whsepack); // WHSE pack
//        row[3] = addCellForInvoice(2,1 , vndrPack); // VNDR pack
//        row[4] = addCellForInvoice(1,1 , totalVNDRPack); // total VNDR packs
//        row[5] = addCellForInvoice(2,1 , totalUnit);  // total unit
//        row[6] = addCellForInvoice(2,1 , netVNDRPack); // net vndr pack
//        row[7] = addCellForInvoice(2,1 , netTotal); // net total
//        row[8] = addCellForInvoice(2,1 , grossVndrPack); // gross vndr pack
//        row[9] = addCellForInvoice(2,1, grossTotal); // gross total
//        row[10] = addCellForInvoice(2,1 , packPrice); // pack price
//        row[11] = addCellForInvoice(2,1 , amountInUSD);  // amount in USD
//        row[12] = addCellForInvoice(1,1 , vndrPackType); // VNDR PACK type
//
//        for(Cell cell: row) {
//            if(cell != null) {
//                invoiceTable.addCell(cell);
//            }
//        }
//    }
//
//    private static void generateAssortmentItems(Table invoiceTable) {
//
//        StringBuilder assortmentBuilder = new StringBuilder();
//        assortmentBuilder.append("ITEM # ").append("980272065").append(System.lineSeparator());
//        assortmentBuilder.append("UPC # ").append("193968069360").append(System.lineSeparator());
//        assortmentBuilder.append("COUNTRY ORIGIN: ").append("CHINA").append(System.lineSeparator());
//
//        createRowForAssortmentInvoiceTable(EMPTY_BLOCK_TXT, assortmentBuilder.toString(), "1", "14", "42",
//                "588", "1175.0508", "49352.1336", "PALLET(S)",
//                "210.0000", "8820.0000", "238.3500", "10010.7000", invoiceTable);
//
//    }
//
//    private static void createRowForInvoiceTable(String shippingMarks, String assortment, String whsepack,
//                                                 String vndrPack, String totalVNDRPack, String totalUnit,
//                                                 String weight, String packPrice, String amountInUSD,
//                                                 String vndrPackType, String netVNDRPack, String netTotal,
//                                                 String grossVndrPack, String grossTotal, Table invoiceTable
//    ) {
//
//        Cell[] row = new Cell[14];
//        row[0] = addCellForInvoice(2,3 , shippingMarks); // shipping marks
//        row[1] = addCellForInvoice(2,6 , assortment); // assortment
//        row[2] = addCellForInvoice(2,1 , whsepack); // WHSE pack
//        row[3] = addCellForInvoice(2,1 , vndrPack); // VNDR pack
//        row[4] = addCellForInvoice(1,1 , totalVNDRPack); // total VNDR packs
//        row[5] = addCellForInvoice(2,1 , totalUnit); // total unit
//        row[6] = addCellForInvoice(1,4 , weight); // weight
//        row[7] = addCellForInvoice(2,1 , packPrice); // pack price
//        row[8] = addCellForInvoice(2,2 , amountInUSD); // amount in USD
//        row[9] = addCellForInvoice(1,1 , vndrPackType);  // VNDR PACK type
//        row[10] = addCellForInvoice(1,1, netVNDRPack); // net vndr pack
//        row[11] = addCellForInvoice(1,1 , netTotal); // net total
//        row[12] = addCellForInvoice(1,1 , grossVndrPack); // gross vndr pack
//        row[13] = addCellForInvoice(1,1 , grossTotal); // gross total
//
//            for(Cell cell: row) {
//                if(cell != null) {
//                    invoiceTable.addCell(cell);
//                }
//            }
//    }
//
//    private static void createRowForItemInInvoiceTable(String shippingMarks, String assortment,
//                                                       String vndrPack, String totalVNDRPack, String totalUnit,
//                                                       String packPrice, String amountInUSD,
//                                                       String netVNDRPack, String netTotal,
//                                                       Table invoiceTable
//    ) {
//
//        Cell[] row = new Cell[14];
//        row[0] = addCellForInvoice(2,3 , shippingMarks); // shipping marks
//        row[1] = addCellForInvoice(2,7 , assortment); // assortment
//        row[2] = addCellForInvoice(2,1 , vndrPack); // VNDR pack
//        row[3] = addCellForInvoice(2,1 , totalVNDRPack); // total VNDR packs
//        row[4] = addCellForInvoice(2,1 , totalUnit); // total unit
//        row[5] = addCellForInvoice(2,1, netVNDRPack); // net vndr pack
//        row[6] = addCellForInvoice(2,1 , netTotal); // net total
//        row[7] = addCellForInvoice(2,1 , ""); // empty field
//        row[8] = addCellForInvoice(2,1 , ""); // empty field
//        row[9] = addCellForInvoice(2,1 , packPrice); // pack price
//        row[10] = addCellForInvoice(2,1 , amountInUSD); // amount in USD
//
//        for(Cell cell: row) {
//            if(cell != null) {
//                invoiceTable.addCell(cell);
//            }
//        }
//    }
//
//
//    private static void createRowForInvoiceTotal(String shippingMarks, String assortment, String whsepack,
//                                                 String vndrPack, String totalVNDRPack, String totalUnit,
//                                                 String packPrice, String amountInUSD,
//                                                 String netVNDRPack, String netTotal,
//                                                 String grossTotal, Table invoiceTable
//    ) {
//
//        Cell[] row = new Cell[14];
//        row[0] = addCellForInvoice(2,3 , shippingMarks); // shipping marks
//        row[1] = addCellForInvoiceTotal(2,6 , assortment); // assortment
//        row[2] = addCellForInvoiceTotalWithBorder(2,1 , whsepack); // WHSE pack
//        row[3] = addCellForInvoiceTotalWithBorder(2,1 , vndrPack); // VNDR pack
//        row[4] = addCellForInvoiceTotalWithBorder(2,1 , totalVNDRPack); // total VNDR packs
//        row[5] = addCellForInvoiceTotalWithBorder(2,1 , totalUnit); // total unit
//        row[6] = addCellForInvoiceTotalWithBorder(2,1, "");
//        row[7] = addCellForInvoiceTotalWithBorder(2,1 , netVNDRPack); // net vndr pack
//        row[8] = addCellForInvoiceTotalWithBorder(2,1 , grossTotal); // gross total
//        row[9] = addCellForInvoiceTotalWithBorder(2,1 , packPrice); // pack price
//        row[10] = addCellForInvoiceTotalWithBorder(2,1 , amountInUSD); // amount in USD
//        row[11] = addCellForInvoiceTotalWithBorder(2,1 , netTotal); // net total
//
//        for(Cell cell: row) {
//            if(cell != null) {
//                invoiceTable.addCell(cell);
//            }
//        }
//    }
//
//    private static void generateShippingMarksRow(String shippingMarks, Table invoiceTable) {
//
////        shippingMarks , assortment , WHSE pack ,VNDR pack, total VNDR packs, total unit, weight, pack price,
////        amount in USD, VNDR PACK type, net vndr pack, net total, gross vndr pack, gross total
//
//        createRowForInvoiceTable(shippingMarks, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,
//                EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,
//                EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,
//                invoiceTable);
//    }
//
//    private static Cell addCellForInvoice(int rowSpan, int colSpan, String content) {
//
//        Paragraph paragraph = new Paragraph(content)
//                .setFontSize(8)
//                .setPaddingLeft(5);
//
//        Cell cell = new Cell(rowSpan, colSpan).add(paragraph);
//        cell.setBorder(Border.NO_BORDER);
//        cell.setTextAlignment(TextAlignment.LEFT);
//        return cell;
//    }
//
//    private static Cell addCellForInvoiceTotalWithBorder(int rowSpan, int colSpan, String content) {
//
//        Paragraph paragraph = new Paragraph(content)
//                .setFontSize(8)
//                .setPaddingLeft(5);
//
//        Cell cell = new Cell(rowSpan, colSpan).add(paragraph);
////        cell.setBorder(Border.NO_BORDER);
//        cell.setTextAlignment(TextAlignment.LEFT);
//        return cell;
//    }
//
//    private static Cell addCellForInvoiceTotal(int rowSpan, int colSpan, String content) {
//        Paragraph paragraph = new Paragraph(content)
//                .setFontSize(8)
//                .setPaddingLeft(5);
//
//        Cell cell = new Cell(rowSpan, colSpan).add(paragraph);
////        cell.setBorder(Border.NO_BORDER);
//        cell.setTextAlignment(TextAlignment.RIGHT);
//        return cell;
//    }
//
//    private static void generateAddress(Document document) {
//        Paragraph title = new Paragraph("MASTER GROUP GLOBAL CO., LIMITED").setFontSize(18)
//                                                                           .setBold()
//                                                                           .setTextAlignment(TextAlignment.CENTER)
//                                                                           .setMarginTop(0);
//        document.add(title);
//
//
//        Paragraph titleAddress = new Paragraph("UNIT 2003,20/F,ORIENT INTERNATIONAL TOWER,1018 TAI NAN WEST STREET,CHEUNG SHA WAN").setFontSize(10)
//                                                                                                                                   .setTextAlignment(TextAlignment.CENTER)
//                                                                                                                                   .setMarginTop(0);
//        document.add(titleAddress);
//
//        Paragraph telephone = new Paragraph().setMarginTop(0)
//                                             .setFontSize(10)
//                                             .setTextAlignment(TextAlignment.CENTER)
//                                             .add("TEL:")
//                                             .add("(852)2312 0409")
//                                             .add(",")
//                                             .add("FAX:")
//                                             .add("(852)2312 0484");
//
//        document.add(telephone);
//        addUnderLine(document);
//
//    }
//
//    private static void addUnderLine(Document document) {
//        LineSeparator lineSeparator = new LineSeparator(new SolidLine());
//        lineSeparator.setMarginTop(1);
//        lineSeparator.setMarginLeft(20);
//        lineSeparator.setMarginRight(20);
//        lineSeparator.setStrokeColor(ColorConstants.BLACK);
//        lineSeparator.setStrokeWidth(0);
//        document.add(lineSeparator);
//    }
//
//    private static void generateOtherInformation(Document document) {
//        Paragraph shipperParagraph = new Paragraph("SHIPPER:").setFontSize(8)
//                                                              .setMarginLeft(20)
//                                                              .setMarginTop(0);
//        Paragraph exporter = new Paragraph("EXPORTER:").setFontSize(8)
//                                                       .setMarginLeft(20)
//                                                       .setMarginTop(0);
//        Paragraph otherInformation = new Paragraph("OTHER INFORMATION:").setFontSize(8)
//                                                                        .setMarginLeft(20)
//                                                                        .setMarginTop(0);
//
//        document.add(shipperParagraph);
//        document.add(exporter);
//        document.add(otherInformation);
//    }
//
//    private static Table generateInvoiceTableHeader(Document document) throws IOException {
//
//        float[] columnWidths = { 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100 , 100 , 100};
//
//        Table invoiceTableHeader = new Table(columnWidths);
//        invoiceTableHeader.setWidth(UnitValue.createPercentValue(100));
//        invoiceTableHeader.setMarginLeft(20);
//        invoiceTableHeader.setMarginRight(20);
//
//        invoiceTableHeader.addCell(new Cell(2, 3).add(getTableHeader("SHIPPING MARKS, PO#,\n PO Type & Dept#")))
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);
//
//        invoiceTableHeader.addCell(new Cell(2, 6).add(getTableHeader("ASSORTMENT/ITEM #, \n COMMERCIAL BRAND & \n DETAILED DESCRIPTION " + "\n AS PER PO")))
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);
//
//        invoiceTableHeader.addCell(new Cell(2, 1).add(getTableHeader("WHSE PACK \n (PCS)")))
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);
//
//        invoiceTableHeader.addCell(new Cell(2, 1).add(getTableHeader("VNDR PACK \n (PCS)")))
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);
//
//        // 5th row
//        invoiceTableHeader.addCell(new Cell(1, 1).add(getTableHeader("TOTAL VNDR PACKS")))
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);
//
//        invoiceTableHeader.addCell(new Cell(2, 1).add(getTableHeader("TOTAL UNITS")))
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);
//
//        // 7th row
//        invoiceTableHeader.addCell(new Cell(1, 4).add(getTableHeader("WEIGHT (KG)")))
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);
//
//        invoiceTableHeader.addCell(new Cell(2, 1).add(getTableHeader("PACK PRICE \n (USD)")))
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);
//
//        invoiceTableHeader.addCell(new Cell(2, 1).add(getTableHeader("AMOUNT IN \n USD")))
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);
//
////         bottom ones
//        invoiceTableHeader.addCell(new Cell(1, 1).add(getTableHeader("VNDR PACK TYPE")))
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);
//
//        invoiceTableHeader.addCell(new Cell(1, 1).add(getTableHeader("NET VNDR \n PACK")))
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);
//
//        invoiceTableHeader.addCell(new Cell(1, 1).add(getTableHeader("NET TOTAL")))
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);
//
//        invoiceTableHeader.addCell(new Cell(1, 1).add(getTableHeader("GROSS VNDR PACK")))
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);
//
//        invoiceTableHeader.addCell(new Cell(1, 1).add(getTableHeader("GROSS TOTAL")))
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);
//
//        return invoiceTableHeader;
//    }
//
//    private static void generatePackingList(PdfDocument pdfDoc, Document document) throws IOException {
//
//        Paragraph packagingList = new Paragraph("COMMERCIAL INVOICE AND PACKING LIST").setFontSize(15)
//                                                                                      .setTextAlignment(TextAlignment.CENTER)
//                                                                                      .setMarginTop(5);
//        document.add(packagingList);
//
//        // Create a table with 2 columns
//        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
//        Table table = new Table(UnitValue.createPercentArray(new float[]{2, 4, 4}));
//        table.setMarginLeft(20);
//        table.setMarginRight(20);
//        table.setMarginBottom(10);
//        table.setBorder(Border.NO_BORDER);
//        table.setWidth(UnitValue.createPercentValue(100));
//
//        Paragraph firstRowParagraph = new Paragraph("SOLD TO:").setFontSize(9)
//                                                               .add(System.lineSeparator())
//                                                               .setTextAlignment(TextAlignment.LEFT)
//                                                               .add("WALMART INC. \n 508 SW 8TH STREET")
//                                                               .add(System.lineSeparator())
//                                                               .add("BENTONVILLE")
//                                                               .add(System.lineSeparator())
//                                                               .add("AR")
//                                                               .add(System.lineSeparator())
//                                                               .add("US")
//                                                               .add(System.lineSeparator())
//                                                               .add("72716");
//
//        Table secondTable = new Table(new float[]{1, 1});
//        secondTable.setWidth(UnitValue.createPercentValue(100));
//        secondTable.setBorder(Border.NO_BORDER);
//
//        Paragraph invoice = new Paragraph("INVOICE #").setFontSize(9);
//        secondTable.addCell(invoice)
//                   .setBorder(Border.NO_BORDER)
//                   .setPadding(0);
//
////         Invoice
//        Paragraph invoiceValue = new Paragraph("3555044").setFontSize(9);
//        secondTable.addCell(invoiceValue)
//                   .setBorder(Border.NO_BORDER)
//                   .setPadding(0);
//
////        COUNTRY OF CONSIGNEE:
//        Paragraph coc = new Paragraph("COUNTRY OF CONSIGNEE:").setFontSize(9);
//        secondTable.addCell(coc)
//                   .setBorder(Border.NO_BORDER);
//
//        Paragraph cocValue = new Paragraph("US").setFontSize(9);
//        secondTable.addCell(cocValue)
//                   .setBorder(Border.NO_BORDER);
//
////      VESSEL NAME:
//        Paragraph vesselName = new Paragraph("VESSEL NAME:").setFontSize(9);
//        secondTable.addCell(vesselName)
//                   .setBorder(Border.NO_BORDER);
//
//        Paragraph vesselNameValue = new Paragraph("GSL LYDIA").setFontSize(9);
//        secondTable.addCell(vesselNameValue)
//                   .setBorder(Border.NO_BORDER);
//
////        SHIPMENT INCOTERMS RULE:
//        Paragraph shipmentRule = new Paragraph("SHIPMENT INCOTERMS RULE:").setFontSize(9);
//        secondTable.addCell(shipmentRule)
//                   .setBorder(Border.NO_BORDER);
//
//        Paragraph shipmentRuleValue = new Paragraph("FOB").setFontSize(9);
//        secondTable.addCell(shipmentRuleValue)
//                   .setBorder(Border.NO_BORDER);
//
////    DEPARTURE/CARGO RECEIVED DATE:
//        Paragraph cargoRecvdDate = new Paragraph("DEPARTURE/CARGO RECEIVED DATE:").setFontSize(9);
//        secondTable.addCell(cargoRecvdDate)
//                   .setBorder(Border.NO_BORDER);
//
//        Paragraph cargoRecvdDateValue = new Paragraph("2024-09-24").setFontSize(9);
//        secondTable.addCell(cargoRecvdDateValue)
//                   .setBorder(Border.NO_BORDER);
//
////        COUNTRY OF DESTINATION:
//        Paragraph destinationCountry = new Paragraph("COUNTRY OF DESTINATION:").setFontSize(9);
//        secondTable.addCell(destinationCountry)
//                   .setBorder(Border.NO_BORDER);
//
//        Paragraph destinationCountryValue = new Paragraph("US").setFontSize(9);
//        secondTable.addCell(destinationCountryValue)
//                   .setBorder(Border.NO_BORDER);
//
////        FROM:
//        Paragraph from = new Paragraph("FROM:").setFontSize(9);
//        secondTable.addCell(from)
//                   .setBorder(Border.NO_BORDER);
//
//        Paragraph fromValue = new Paragraph("YANTIAN").setFontSize(9);
//        secondTable.addCell(fromValue)
//                   .setBorder(Border.NO_BORDER);
//
//        Paragraph fromSmaller = new Paragraph("(PORT/PLACE OF LOADING)").setFontSize(5);
//        secondTable.addCell(fromSmaller)
//                   .setBorder(Border.NO_BORDER);
//
////  Third Row Paragraph
//
////        Paragraph thirdParagraph = new Paragraph();
//        Table thirdTable = new Table(new float[]{1, 1});
//        thirdTable.setWidth(UnitValue.createPercentValue(100));
//        thirdTable.setBorder(Border.NO_BORDER);
//
////        DATE:
//        Paragraph toDate = new Paragraph("DATE:").setFontSize(9);
//        thirdTable.addCell(toDate)
//                  .setBorder(Border.NO_BORDER);
//
//        Paragraph toDateValue = new Paragraph("2024-09-24").setFontSize(9);
//        thirdTable.addCell(toDateValue)
//                  .setBorder(Border.NO_BORDER);
//
////        VOYAGE/FLIGHT #
//        Paragraph voyageFlightNum = new Paragraph("VOYAGE/FLIGHT #").setFontSize(9);
//        thirdTable.addCell(voyageFlightNum)
//                  .setBorder(Border.NO_BORDER);
//
//        Paragraph voyageFlightNumValue = new Paragraph("439W").setFontSize(9);
//        thirdTable.addCell(voyageFlightNumValue)
//                  .setBorder(Border.NO_BORDER);
//
////        TRANSPORT MODE:
//        Paragraph transPortMode = new Paragraph("VOYAGE/FLIGHT #").setFontSize(9);
//        thirdTable.addCell(transPortMode)
//                  .setBorder(Border.NO_BORDER);
//
//        Paragraph transPortModeValue = new Paragraph("OCEAN").setFontSize(9);
//        thirdTable.addCell(transPortModeValue)
//                  .setBorder(Border.NO_BORDER);
////        thirdParagraph.add("\n");
//
////        NAMED PLACE:
//        Paragraph namedPlace = new Paragraph("NAMED PLACE:").setFontSize(9);
//        thirdTable.addCell(namedPlace)
//                  .setBorder(Border.NO_BORDER);
//
//        Paragraph namedPlaceValue = new Paragraph("YANTIAN").setFontSize(9);
//        thirdTable.addCell(namedPlaceValue)
//                  .setBorder(Border.NO_BORDER);
//
////        COUNTRY OF LOADING:
//        Paragraph lodingCountry = new Paragraph("COUNTRY OF LOADING:").setFontSize(9);
//        thirdTable.addCell(lodingCountry)
//                  .setBorder(Border.NO_BORDER);
//
//        Paragraph lodingCountryValue = new Paragraph("CN").setFontSize(9);
//        thirdTable.addCell(lodingCountryValue)
//                  .setBorder(Border.NO_BORDER);
//
//// COUNTRY OF ORIGIN:
//        Paragraph originCountry = new Paragraph("COUNTRY OF ORIGIN:").setFontSize(9);
//        thirdTable.addCell(originCountry)
//                  .setBorder(Border.NO_BORDER);
//
//        Paragraph originCountryValue = new Paragraph("CN").setFontSize(9);
//        thirdTable.addCell(originCountryValue)
//                  .setBorder(Border.NO_BORDER);
//
////        TO:
//        Paragraph to = new Paragraph("TO:").setFontSize(9);
//        thirdTable.addCell(to)
//                  .setBorder(Border.NO_BORDER);
//
//        Paragraph toValue = new Paragraph("CHARLESTON").setFontSize(9);
//        thirdTable.addCell(toValue)
//                  .setBorder(Border.NO_BORDER);
//
//        Paragraph toSmaller = new Paragraph("(FINAL DESTINATION IN THE PO)").setFontSize(5);
//        thirdTable.addCell(toSmaller)
//                  .setBorder(Border.NO_BORDER);
//
//        table.addCell(firstRowParagraph)
//             .setBorder(Border.NO_BORDER);
//        table.addCell(secondTable)
//             .setBorder(Border.NO_BORDER);
//        table.addCell(thirdTable)
//             .setBorder(Border.NO_BORDER);
//
//
//        Table borderLessTable = removeBorder(table);
//        document.add(borderLessTable);
//    }
//
//
//    private static void updatePageHeaders(HeaderEventHandler headerEventHandler, String sourcePdfPath ,
//                                          String targetPdfPath) throws IOException {
//
//        System.out.println("re-opening the docs after saving ==========");
//        PdfReader reader = new PdfReader(sourcePdfPath);
//        PdfWriter finalWriter = new PdfWriter(targetPdfPath);
//        PdfDocument updatedPdf = new PdfDocument(reader, finalWriter);
//
//        int totalPageCount = updatedPdf.getNumberOfPages();
//
//        // Update the header on each page
//        for (int pageNumber = 1; pageNumber <= totalPageCount; pageNumber++) {
//            PdfPage page = updatedPdf.getPage(pageNumber);
//            headerEventHandler.updatePageHeaders(page, pageNumber, totalPageCount);
//        }
//
//        updatedPdf.close();
//
//    }
//
//    public static Table removeBorder(Table table) {
//        // Loop through the outer table cells and check for inner tables
//        for (int i = 0; i < table.getNumberOfRows(); i++) {
//            for (int j = 0; j < table.getNumberOfColumns(); j++) {
//                Cell cell = table.getCell(i, j);
//                if (cell != null && cell.getChildren() != null) {
//                    for (com.itextpdf.layout.element.IElement element : cell.getChildren()) {
//                        if (element instanceof Table) {
//                            Table inner = (Table) element;
//                            setTableBordersToNoBorder(inner);
//                        }
//                    }
//                }
//                // Set the outer table cell borders to no border
//                if(cell != null) {
//                    cell.setBorder(Border.NO_BORDER);
//                }
//            }
//        }
//        return table;
//    }
//
//
//
//
//    private static void setTableBordersToNoBorder(Table table) {
//        for (int i = 0; i < table.getNumberOfRows(); i++) {
//            for (int j = 0; j < table.getNumberOfColumns(); j++) {
//                Cell cell = table.getCell(i, j);
//                if (cell != null) {
//                    cell.setBorder(Border.NO_BORDER);
//                }
//            }
//        }
//    }
//
//    public static Paragraph getTableHeader(String name) throws IOException {
//
//        PdfFont helveticaFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
//
//        return  new Paragraph(name)
//                .setFont(helveticaFont)
//                .setTextAlignment(TextAlignment.CENTER)
//                .setFontSize(7);
//
//    }
//
//}
//
//class HeaderEventHandler implements IEventHandler {
//
//    private final String soldTo;
//    private final String invoiceNumber;
//    private final String date;
//    private int totalPageCount = 0;
//
//    public HeaderEventHandler(String soldTo, String invoiceNumber, String date) {
//        this.soldTo = soldTo;
//        this.invoiceNumber = invoiceNumber;
//        this.date = date;
//    }
//
//    public void setTotalPageCount(int totalPageCount) {
//        this.totalPageCount = totalPageCount;
//    }
//
//
//    @Override
//    public void handleEvent(Event event) {
//        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
//        PdfPage page = docEvent.getPage();
//        int pageNumber = docEvent.getDocument()
//                                 .getPageNumber(page);
//
//        if (page != null) {
//            updatePageHeaders(page, pageNumber, totalPageCount);
//        }
//
//    }
//
//    public void updatePageHeaders(PdfPage page, int pageNumber, int totalPageCount) {
//        if (page == null || pageNumber <= 1) {
//            System.out.println("Page null or first page, hence skipped headers");
//            return;
//        }
//
//        System.out.println("===================== processing the headers for the pageNumber: " + pageNumber);
//
//        PdfCanvas pdfCanvas = new PdfCanvas(page);
//        Rectangle pageSize = page.getPageSize();
//
//        float leftMargin = 20;
//        float rightMargin = 20;
//
//        Table headerTable =
//                new Table(UnitValue.createPercentArray(new float[]{1, 1, 1, 1})).useAllAvailableWidth();
//
//        String pageCount = totalPageCount > 0 ? totalPageCount + "" : "##";
//
//        headerTable.addCell(createHeaderCell("SOLD TO: " + soldTo, true));
//        headerTable.addCell(createHeaderCell("INVOICE #: " + invoiceNumber, true));
//        headerTable.addCell(createHeaderCell("DATE: " + date, true));
//        headerTable.addCell(createHeaderCell("PAGE " + pageNumber + " of " + pageCount, false));
//
//        float headerY = pageSize.getTop() - 30;
//        float width = pageSize.getWidth() - leftMargin - rightMargin;
//        float height = 30;
//
//        // Adjust Rectangle for margins
//        Canvas canvas = new Canvas(pdfCanvas, new Rectangle(leftMargin, headerY, width, height));
//        canvas.add(headerTable);
//        canvas.close();
//    }
//
//    private Cell createHeaderCell(String content, boolean bold) {
//        Paragraph paragraph = new Paragraph(content).setFontSize(10)
//                                                    .setTextAlignment(TextAlignment.CENTER);
//
//        if (bold) {
//            paragraph.setBold();
//        }
//
//        return new Cell().add(paragraph)
//                         .setBorder(Border.NO_BORDER)
//                         .setBackgroundColor(ColorConstants.WHITE);
//    }
//
//
//}