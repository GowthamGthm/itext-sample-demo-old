package com.gthm.itext.openpdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfDocument;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class OpenPDFSample {

    static String directory = "C:\\Users\\anon\\Pictures\\output-invoide\\";
    private static String PATH = directory + "my-lowa.pdf";

    private static String EMPTY_BLOCK_TXT = """

        """;
    private static String TAB = "\t";

    static Font HELVETICA_SIZE_5 = new Font(Font.HELVETICA, 5);
    static Font HELVETICA_SIZE_9 = new Font(Font.HELVETICA, 9);
    static Font HELVETICA_SIZE_8 = new Font(Font.HELVETICA, 8);
    static Font HELVETICA_SIZE_10 = new Font(Font.HELVETICA, 10);
    static Font HELVETICA_SIZE_15 = new Font(Font.HELVETICA , 15);

    static Font HELVETICA_SIZE_8_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD);
    static Font HELVETICA_SIZE_18_BOLD = new Font(Font.HELVETICA, 18, Font.BOLD);

    public static void main(String[] args) {

        try {

            Document document = new Document(PageSize.A4.rotate());
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PATH));
            PdfDocument pdfDoc = new PdfDocument();
            pdfDoc.addWriter(writer);

            document.open();
//            document.setMargins(36, 20, 36, 20);
            document.setMargins(20, 20, 30, 20);

//          starting to construct the PDF
            generateAddress(document , writer);
            generatePackingList(document);
            generateOtherInformation(document);
            PdfPTable invoiceTable = generateInvoiceTableHeader();
            generateTheDataInInvoiceTable(document, invoiceTable);

//            generate total in words
            Paragraph totalAmountInWords =
                    new Paragraph("INVOICE TOTAL VALUE IN US DOLLARS FORTY-NINE THOUSAND THREE HUNDRED " +
                            "AND FIFTY TWO AND CENTS THIRTEEN");
            totalAmountInWords.setSpacingBefore(15);
            totalAmountInWords.setFont(HELVETICA_SIZE_9);
            totalAmountInWords.setIndentationLeft(30);
            totalAmountInWords.setAlignment(Element.ALIGN_LEFT);
            totalAmountInWords.setSpacingAfter(20);
            document.add(totalAmountInWords);

//            generateUnder line and extra Stuff
            generateUnderLineAndExtraStuff(document);
            // close the document
            document.close();

//          start generating the page number
            String outputFilePath =  directory + File.separator + "open-pdf-page-num.pdf";
            OpenPdfHeaderWithPageNumber.manipulatePdf(PATH , outputFilePath, "WALMART INC",
                    "355670", "10/06/2024");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void generateAddress(Document document, PdfWriter writer) throws DocumentException {
        Paragraph title = OpenPDFUtil.createParagraph("MASTER GROUP GLOBAL CO., LIMITED" ,
                HELVETICA_SIZE_18_BOLD , Element.ALIGN_CENTER,
                null);
        document.add(title);

        Paragraph titleAddress = OpenPDFUtil.createParagraph("UNIT 2003,20/F,ORIENT INTERNATIONAL TOWER,1018 TAI NAN WEST STREET,CHEUNG SHA WAN",
                HELVETICA_SIZE_10, Element.ALIGN_CENTER,null);

        document.add(titleAddress);

        StringBuilder telephoneBuilder = new StringBuilder();
        telephoneBuilder.append("TEL:")
                        .append("(852)2312 0409")
                        .append(",")
                        .append("FAX:")
                        .append("(852)2312 0484");

        Paragraph telephone = OpenPDFUtil.createParagraph(telephoneBuilder.toString() , HELVETICA_SIZE_10,
                Element.ALIGN_CENTER , LowagieMargin.builder().bottom(0f).build());

        document.add(telephone);
        OpenPDFUtil.addUnderLine(document);

    }


    private static void generateUnderLineAndExtraStuff(Document document) throws DocumentException {
        OpenPDFUtil.addUnderLine(document);

        Paragraph descriptionPara = new
                Paragraph("DESCRIPTION, ORIGIN, QUANTITY, COST OF ANY FREE SUPPLIED PART(S):");
        descriptionPara.setFont(HELVETICA_SIZE_8);
        descriptionPara.setAlignment(Element.ALIGN_LEFT);
        descriptionPara.setIndentationLeft(30);

        document.add(descriptionPara);

        OpenPDFUtil.addUnderLine(document);

//         signature
        Paragraph namePara = new Paragraph("JANE");
        namePara.setFont(HELVETICA_SIZE_8);
        namePara.setAlignment(Element.ALIGN_LEFT);
        namePara.setSpacingBefore(20);
        namePara.setIndentationLeft(30);

        document.add(namePara);

        Paragraph shippingSpecialist = OpenPDFUtil.createParagraph("SHIPPING SPECIALIST", HELVETICA_SIZE_8, Element.ALIGN_LEFT,
                LowagieMargin.builder().left(30f).build());

        document.add(shippingSpecialist);

    }

    private static void generateInvoiceTotalRow(PdfPTable invoiceTable) {

        String invoiceTotal = "INVOICE TOTAL ";

//        7,10,13
        OpenPDFUtil.createRowForInvoiceTotal("", invoiceTotal, "1", "14", "42",
                "588",  "83.9322", "49352.13",
                "210.0000", "8820.0000",  "10010.7000", invoiceTable);

    }

    private static void generateTheDataInInvoiceTable(Document document, PdfPTable invoiceTable) throws DocumentException {

        //         generate shippingMarks
        String shippingMarks = "To US Case ID\nPO # 2923267225\nPo Type # 43\nDept # 9\nItem # 595469863\nSupplier STK # BF-PC";
        generateShippingMarksRow(shippingMarks , invoiceTable);

//         generate values for ASSORTMENT ITEMS
        generateAssortmentItems(invoiceTable);

//        generate product description
         generateProductDescription(invoiceTable);

//          generate item row
        generateItem(invoiceTable);

        //   generate invoice Total Row
        generateInvoiceTotalRow(invoiceTable);

//         add invoice table to the document at last of adding all RoWS
        document.add(invoiceTable);

    }

    private static void generateItem(PdfPTable invoiceTable) {

        StringBuilder builder = new StringBuilder();
        builder.append("ITEM # ").append("980272065").append(System.lineSeparator());


//        shippingMarks , assortment , WHSE pack ,VNDR pack, total VNDR packs,
//        total unit, weight, pack price,amount in USD, VNDR PACK type,
//        net vndr pack, net total, gross vndr pack, gross total

        OpenPDFUtil.createRowForItemInInvoiceTable(EMPTY_BLOCK_TXT, builder.toString(),  "14",
                "42", "588",  "83.9322", "49352.1336",
                 "210.0000", "8820.0000",
                 invoiceTable);

        builder.setLength(0);
        builder.append("ITEM DESCRIPTION: ").append("15PC HARD ANODIZED COOKEWARE SET").append(System.lineSeparator());
        builder.append(TAB).append("  COMPONENT DETAILS: ").append(System.lineSeparator());

        OpenPDFUtil.createExpandingRowForInvoiceTable(EMPTY_BLOCK_TXT, builder.toString(), EMPTY_BLOCK_TXT,
                EMPTY_BLOCK_TXT,  EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,
                EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,  invoiceTable);

//         clear builder to reuse
        builder.setLength(0);

//        build the Common Name
        for (int i = 0; i < 5; i++) {

            builder.append(TAB).append("   COMMON NAME: ").append("2QT COVERED SAUCE PAN").append(System.lineSeparator());
            builder.append(TAB).append(TAB).append("    BREAKDOWN:").append(System.lineSeparator());
            builder.append(TAB).append(TAB).append("    STAINLESS STEEL - ").append("20%").append(System.lineSeparator());
            builder.append(TAB).append(TAB).append("    GLASS - ").append("25%").append(System.lineSeparator());
            builder.append(TAB).append(TAB).append("    ALUMINUM - ").append("55%").append(System.lineSeparator());
            builder.append(TAB).append("   VALUE: ").append("7.8391").append(System.lineSeparator())
                   .append(System.lineSeparator()).append(System.lineSeparator());

            //     5 , 10, 12 ,13 , 14
            OpenPDFUtil.createExpandingRowForInvoiceTable(EMPTY_BLOCK_TXT, builder.toString(), EMPTY_BLOCK_TXT,
                    EMPTY_BLOCK_TXT,  EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,
                    EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,  EMPTY_BLOCK_TXT,
                    invoiceTable);

        }

        builder.setLength(0);
        builder.append("ASSEMBLY MANUFACTURER:").append(System.lineSeparator());
        builder.append("NAME: ").append("GUANGDONG MASTER GROUP CO., LTD").append(System.lineSeparator());
        builder.append("ADDRESS: ")
               .append("NO. 48-50 SOUTH SECTION, DANAN ROAD, XINXING COUNTY, YUNFU, GD, CN, 527300, 8618023382230")
               .append(System.lineSeparator())
               .append(System.lineSeparator())
               .append(System.lineSeparator());


        OpenPDFUtil.createExpandingRowForInvoiceTable(EMPTY_BLOCK_TXT, builder.toString(), EMPTY_BLOCK_TXT,
                EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,
                EMPTY_BLOCK_TXT,  EMPTY_BLOCK_TXT,  invoiceTable);

    }

    private static void generateShippingMarksRow(String shippingMarks, PdfPTable invoiceTable) {

//        shippingMarks , assortment , WHSE pack ,VNDR pack, total VNDR packs, total unit, weight, pack price,
//        amount in USD, VNDR PACK type, net vndr pack, net total, gross vndr pack, gross total

        OpenPDFUtil.createRowForInvoiceTable(shippingMarks, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,
                EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,
                EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,
                invoiceTable);
    }

    private static void generateProductDescription(PdfPTable invoiceTable) {
        StringBuilder builder = new StringBuilder();
        builder.append("PRODUCT DESCRIPTION: ").append("15PC HARD ANODIZED COOKEWARE SET").append(System.lineSeparator());
        builder.append("ADDITIONAL PRODUCT DESCRIPTION: ").append(System.lineSeparator()).append(System.lineSeparator());
        builder.append("VENDOR STK # ").append("15PCHA").append(System.lineSeparator());


//        createRowForIncomeTable(DUMMY_TEXT, builder.toString(), DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT,
//                DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT,
//                DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, DUMMY_TEXT, invoiceTable);

        OpenPDFUtil.createExpandingRowForInvoiceTable(EMPTY_BLOCK_TXT, builder.toString(), EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,
                 EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT, EMPTY_BLOCK_TXT,
                 EMPTY_BLOCK_TXT, invoiceTable);

    }


//    7,
    private static void createRowForAssortmentInvoiceTable(String shippingMarks, String assortment, String whsepack,
                                                           String vndrPack, String totalVNDRPack, String totalUnit,
                                                           String packPrice, String amountInUSD,
                                                           String vndrPackType, String netVNDRPack, String netTotal,
                                                           String grossVndrPack, String grossTotal, PdfPTable invoiceTable
    ) {

        PdfPCell[] row = new PdfPCell[14];
        row[0] = OpenPDFUtil.addCellForInvoice(2,3 , shippingMarks); // shipping marks
        row[1] = OpenPDFUtil.addCellForInvoice(2,6 , assortment); // assortment
        row[2] = OpenPDFUtil.addCellForInvoice(2,1 , whsepack); // WHSE pack
        row[3] = OpenPDFUtil.addCellForInvoice(2,1 , vndrPack); // VNDR pack
        row[4] = OpenPDFUtil.addCellForInvoice(1,1 , totalVNDRPack); // total VNDR packs
        row[5] = OpenPDFUtil.addCellForInvoice(2,1 , totalUnit);  // total unit
        row[6] = OpenPDFUtil.addCellForInvoice(2,1 , netVNDRPack); // net vndr pack
        row[7] = OpenPDFUtil.addCellForInvoice(2,1 , netTotal); // net total
        row[8] = OpenPDFUtil.addCellForInvoice(2,1 , grossVndrPack); // gross vndr pack
        row[9] = OpenPDFUtil.addCellForInvoice(2,1, grossTotal); // gross total
        row[10] = OpenPDFUtil.addCellForInvoice(2,1 , packPrice); // pack price
        row[11] = OpenPDFUtil.addCellForInvoice(2,1 , amountInUSD);  // amount in USD
        row[12] = OpenPDFUtil.addCellForInvoice(1,1 , vndrPackType); // VNDR PACK type

        for(PdfPCell cell: row) {
            if(cell != null) {
                invoiceTable.addCell(cell);
//                 TODO: remove this border later
                cell.setBorder(Rectangle.BOX);
            }
        }
    }

    private static void generateAssortmentItems(PdfPTable invoiceTable) {

        StringBuilder assortmentBuilder = new StringBuilder();
        assortmentBuilder.append("ITEM # ").append("980272065").append(System.lineSeparator());
        assortmentBuilder.append("UPC # ").append("193968069360").append(System.lineSeparator());
        assortmentBuilder.append("COUNTRY ORIGIN: ").append("CHINA").append(System.lineSeparator());

        createRowForAssortmentInvoiceTable(EMPTY_BLOCK_TXT, assortmentBuilder.toString(), "1", "14", "42",
                "588", "1175.0508", "49352.1336", "PALLET(S)",
                "210.0000", "8820.0000", "238.3500", "10010.7000", invoiceTable);

    }


    private static PdfPTable generateInvoiceTableHeader() throws IOException {

        float[] columnWidths = { 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100 , 100 , 100};

//        float[] columnWidths = {  100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100 , 100 , 100};


        PdfPTable invoiceTableHeader = new PdfPTable(columnWidths);
        invoiceTableHeader.setWidthPercentage(100);

        invoiceTableHeader.addCell(OpenPDFUtil.getTableHeader("SHIPPING MARKS, PO#,\n PO Type & Dept#" , 2 , 3));

        invoiceTableHeader.addCell(OpenPDFUtil.getTableHeader("ASSORTMENT/ITEM #, \n COMMERCIAL BRAND & \n DETAILED DESCRIPTION "
                + "\n AS PER PO", 2, 6));

        invoiceTableHeader.addCell(OpenPDFUtil.getTableHeader("WHSE PACK \n (PCS)" , 2 , 1));

        invoiceTableHeader.addCell(OpenPDFUtil.getTableHeader("VNDR PACK \n (PCS)" , 2 ,1));

        // 5th row
        invoiceTableHeader.addCell(OpenPDFUtil.getTableHeader("TOTAL VNDR PACKS" , 1,1));
//                          .setVerticalAlignment(VerticalAlignment.MIDDLE)
//                          .setTextAlignment(TextAlignment.CENTER);

        invoiceTableHeader.addCell(OpenPDFUtil.getTableHeader("TOTAL UNITS" , 2,1));

        // 7th row
        invoiceTableHeader.addCell(OpenPDFUtil.getTableHeader("WEIGHT (KG)" , 1, 4));

        invoiceTableHeader.addCell(OpenPDFUtil.getTableHeader("PACK PRICE \n (USD)" , 2, 1));

        invoiceTableHeader.addCell(OpenPDFUtil.getTableHeader("AMOUNT IN \n USD" , 2 ,1));

//         bottom ones
        invoiceTableHeader.addCell(OpenPDFUtil.getTableHeader("VNDR PACK TYPE" , 1,1));

        invoiceTableHeader.addCell(OpenPDFUtil.getTableHeader("NET VNDR \n PACK" , 1, 1));

        invoiceTableHeader.addCell(OpenPDFUtil.getTableHeader("NET TOTAL" , 1,1));

        invoiceTableHeader.addCell(OpenPDFUtil.getTableHeader("GROSS VNDR PACK" , 1,1));

        invoiceTableHeader.addCell(OpenPDFUtil.getTableHeader("GROSS TOTAL" , 1,1));

        return invoiceTableHeader;
    }



    private static void generateOtherInformation(Document document) throws DocumentException {

        LowagieMargin margin = LowagieMargin.builder()
                                           .left(20f)
                                           .build();

        Paragraph shipperParagraph = OpenPDFUtil.createParagraph("SHIPPER:" , HELVETICA_SIZE_8,
                Element.ALIGN_LEFT, null);
        Paragraph exporter =  OpenPDFUtil.createParagraph("EXPORTER:" , HELVETICA_SIZE_8,
                Element.ALIGN_LEFT, null);
        Paragraph otherInformation = OpenPDFUtil.createParagraph("OTHER INFORMATION:" , HELVETICA_SIZE_8,
                Element.ALIGN_LEFT, null);

        Paragraph marginBelow = new Paragraph("");
        marginBelow.setSpacingAfter(10f);

        document.add(shipperParagraph);
        document.add(exporter);
        document.add(otherInformation);
        document.add(marginBelow);
    }



    private static void generatePackingList(Document document) throws IOException, DocumentException {

        Paragraph packagingList = OpenPDFUtil.createParagraph("COMMERCIAL INVOICE AND PACKING LIST" , HELVETICA_SIZE_15,  Element.ALIGN_CENTER ,
                LowagieMargin.builder().top(0f).bottom(10f).build());

        document.add(packagingList);

        // Create a table with 2 columns
        PdfPTable table = new PdfPTable(new float[]{2, 4, 4});
        table.getDefaultCell().setPaddingLeft(20);
        table.getDefaultCell().setPaddingRight(20);
        table.getDefaultCell().setPaddingBottom(10);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        table.setWidthPercentage(100);

        StringBuilder builder = new StringBuilder();
        builder.append("SOLD TO:")
                .append(System.lineSeparator())
               .append("WALMART INC. \n508 SW 8TH STREET")
               .append(System.lineSeparator())
               .append("BENTONVILLE")
               .append(System.lineSeparator())
               .append("AR")
               .append(System.lineSeparator())
               .append("US")
               .append(System.lineSeparator())
               .append("72716");

        Paragraph firstRowParagraph = OpenPDFUtil.createParagraph(builder.toString(), HELVETICA_SIZE_9,
                Element.ALIGN_LEFT, null);

        PdfPTable secondTable = new PdfPTable(new float[]{1, 1});
        secondTable.setWidthPercentage(100);

        PdfPCell invoice = OpenPDFUtil.getPdfpCellForFromTO("INVOICE #");
        secondTable.addCell(invoice);

//         Invoice
        PdfPCell invoiceValue = OpenPDFUtil.getPdfpCellForFromTO("3555044");
        secondTable.addCell(invoiceValue);

//        COUNTRY OF CONSIGNEE:
        PdfPCell coc = OpenPDFUtil.getPdfpCellForFromTO("COUNTRY OF CONSIGNEE:");
        secondTable.addCell(coc);

        PdfPCell cocValue = OpenPDFUtil.getPdfpCellForFromTO("US");
        secondTable.addCell(cocValue);

//      VESSEL NAME:
        PdfPCell vesselName = OpenPDFUtil.getPdfpCellForFromTO("VESSEL NAME:");
        secondTable.addCell(vesselName);

        PdfPCell vesselNameValue = OpenPDFUtil.getPdfpCellForFromTO("GSL LYDIA");
        secondTable.addCell(vesselNameValue);

//        SHIPMENT INCOTERMS RULE:
        PdfPCell shipmentRule = OpenPDFUtil.getPdfpCellForFromTO("SHIPMENT INCOTERMS RULE:");
        secondTable.addCell(shipmentRule);

        PdfPCell shipmentRuleValue = OpenPDFUtil.getPdfpCellForFromTO("FOB");
        secondTable.addCell(shipmentRuleValue);

//    DEPARTURE/CARGO RECEIVED DATE:
        PdfPCell cargoRecvdDate = OpenPDFUtil.getPdfpCellForFromTO("DEPARTURE/CARGO RECEIVED DATE:");
        secondTable.addCell(cargoRecvdDate);

        PdfPCell cargoRecvdDateValue = OpenPDFUtil.getPdfpCellForFromTO("2024-09-24");
        secondTable.addCell(cargoRecvdDateValue);

//        COUNTRY OF DESTINATION:
        PdfPCell destinationCountry = OpenPDFUtil.getPdfpCellForFromTO("COUNTRY OF DESTINATION:");
        secondTable.addCell(destinationCountry);

        PdfPCell destinationCountryValue = OpenPDFUtil.getPdfpCellForFromTO("US");
        secondTable.addCell(destinationCountryValue);

//        FROM:
        PdfPCell from = OpenPDFUtil.getPdfpCellForFromTO("FROM:");
        secondTable.addCell(from);

        PdfPCell fromValue = OpenPDFUtil.getPdfpCellForFromTO("YANTIAN");
        secondTable.addCell(fromValue);

//         from smaller
        PdfPCell fromSmaller = OpenPDFUtil.getPdfpCellForFromTOSmaller("(PORT/PLACE OF LOADING)");
        secondTable.addCell(fromSmaller);

        PdfPCell fromSmallerValue = OpenPDFUtil.getPdfpCellForFromTOSmaller("");
        secondTable.addCell(fromSmallerValue);

//  Third Row Paragraph

        PdfPTable thirdTable = new PdfPTable(new float[]{1, 1});
        thirdTable.setWidthPercentage(100);

//        DATE:
        PdfPCell toDate = OpenPDFUtil.getPdfpCellForFromTO("DATE:");
        thirdTable.addCell(toDate);

        PdfPCell toDateValue = OpenPDFUtil.getPdfpCellForFromTO("2024-09-24");
        thirdTable.addCell(toDateValue);


//        VOYAGE/FLIGHT #
        PdfPCell voyageFlightNum = OpenPDFUtil.getPdfpCellForFromTO("VOYAGE/FLIGHT #");
        thirdTable.addCell(voyageFlightNum);


        PdfPCell voyageFlightNumValue = OpenPDFUtil.getPdfpCellForFromTO("439W");
        thirdTable.addCell(voyageFlightNumValue);


//        TRANSPORT MODE:
        PdfPCell transPortMode = OpenPDFUtil.getPdfpCellForFromTO("VOYAGE/FLIGHT #");
        thirdTable.addCell(transPortMode);

        PdfPCell transPortModeValue =  OpenPDFUtil.getPdfpCellForFromTO("OCEAN");
        thirdTable.addCell(transPortModeValue);

//        NAMED PLACE:
        PdfPCell namedPlace =  OpenPDFUtil.getPdfpCellForFromTO("NAMED PLACE:");
        thirdTable.addCell(namedPlace);

        PdfPCell namedPlaceValue = OpenPDFUtil.getPdfpCellForFromTO("YANTIAN");
        thirdTable.addCell(namedPlaceValue);

//        COUNTRY OF LOADING:
        PdfPCell lodingCountry = OpenPDFUtil.getPdfpCellForFromTO("COUNTRY OF LOADING:");
        thirdTable.addCell(lodingCountry);

        PdfPCell lodingCountryValue = OpenPDFUtil.getPdfpCellForFromTO("CN");
        thirdTable.addCell(lodingCountryValue);


// COUNTRY OF ORIGIN:
        PdfPCell originCountry = OpenPDFUtil.getPdfpCellForFromTO("COUNTRY OF ORIGIN:");
        thirdTable.addCell(originCountry);

        PdfPCell originCountryValue = OpenPDFUtil.getPdfpCellForFromTO("CN");
        thirdTable.addCell(originCountryValue);


//        TO:
        PdfPCell to = OpenPDFUtil.getPdfpCellForFromTO("TO:");
        thirdTable.addCell(to);

        PdfPCell toValue = OpenPDFUtil.getPdfpCellForFromTO("CHARLESTON");
        thirdTable.addCell(toValue);

        //        TO Smaller:
        PdfPCell toSmaller = OpenPDFUtil.getPdfpCellForFromTOSmaller("(FINAL DESTINATION IN THE PO)");
        thirdTable.addCell(toSmaller);

        PdfPCell toSmallerValue = OpenPDFUtil.getPdfpCellForFromTOSmaller("");
        thirdTable.addCell(toSmallerValue);


        PdfPCell firstTableCell = new PdfPCell(firstRowParagraph);
        PdfPCell secondTableCell = new PdfPCell(secondTable);
        PdfPCell thirdTableCell = new PdfPCell(thirdTable);

        firstTableCell.setBorder(Rectangle.NO_BORDER);
        secondTableCell.setBorder(Rectangle.NO_BORDER);
        thirdTableCell.setBorder(Rectangle.NO_BORDER);

        table.addCell(firstTableCell);
        table.addCell(secondTableCell);
        table.addCell(thirdTableCell);



//        PdfPTable borderLessTable = OpenPDFUtil.removeBorder(table);
        document.add(table);
    }



}