package ricoh.core.fileutils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.winium.WiniumDriver;

import ricoh.config.gblConstants;
import ricoh.core.accelerators.WindowsActionEngine;
import ricoh.core.logs.LogManager;

@SuppressWarnings({ "resource", "unused" })
public class PDFReader {

	private PDFTextStripper pdfTextStripper;
	private String pdfFileLocation = null;
	public WiniumDriver driver= null;
	public WindowsActionEngine	windowsActEngine =new WindowsActionEngine();
	public String exportedPDFFilePath=gblConstants.projWorkingDir+File.separator+"ExportedFiles"+File.separator+"PDFFiles";
	public final String className = PDFReader.class.getName();
	
	public PDFReader(String pdfFileLocation) throws Throwable {
		this.pdfFileLocation = pdfFileLocation;
	}
	

	/**
	 * @Purpose To get page Wise Text from PDF document

	 */

	public String[] getPageWiseTextFromPDF() throws Throwable, IOException{

		List<PDDocument> pages = (new org.apache.pdfbox.multipdf.Splitter()).split(PDDocument.load(new File(pdfFileLocation)));
		
		System.out.println(pages.get(0).getPage(1));
		
		String[] arrayOfPageText = new String[pages.size()];
		try {
			for(int i = 0;i<=pages.size()-1;i++){
				System.out.println(pdfTextStripper.getText(pages.get(0)));
				arrayOfPageText[i] = pdfTextStripper.getText(pages.get(i));
			}
		} catch (FileNotFoundException e) {
			LogManager.logException(e, className, "Exception to find file");
		} catch (IOException e) {
			LogManager.logException(e, className, "Exception to read file");
		};
		return arrayOfPageText;

		/*  
		 *  To get content of all pages in single string
		 *  use string = pdfTextStripper.getText(pdDocument);
		 *  But the content would fit only as much as string's memory.
		 *  better don't try to get everything in one string.
		 *  
		 */
	}


	@SuppressWarnings("finally")
	private String getRequestedPDFPageContent(int pageNumber) throws Throwable{
		PDDocument pdDoc = PDDocument.load(new File(pdfFileLocation));
		List<PDDocument> pages = (new Splitter()).split(pdDoc);

		String content = null;
		String PageText = null;
		String searchKey = null;
		try {
			PageText = pdfTextStripper.getText(pages.get(pageNumber));
			searchKey ="Fraud and Cyber Defense Coverage";

			Scanner scanner = new Scanner(PageText);
			while(scanner.hasNextLine()){
				String lineRead = scanner.nextLine();
				String lineReadTrim = lineRead.trim().replace(" ", "");
				if(lineReadTrim.contains(searchKey.trim().replace(" ","")))
					content = PageText;						
			}
			scanner.close();
		}
		catch (FileNotFoundException e) {
			LogManager.logException(e, className, "Exception to find file");
		}catch (IOException e) {
			LogManager.logException(e, className, "Exception to read file");
		}finally{
			if( pdDoc != null ) {
				for (PDDocument pdDocument : pages) {
					pdDocument.close();
				}
				pdDoc.close();
			}
			return content;
		}
	}



	//*** Split String pageContent to String[] lines ***\\	

	private String[] convertPageContentToStringLines(String pageContent) {
		String[] substrings = null;
		Scanner scanner = new Scanner(pageContent);
		while(scanner.hasNextLine()){
			String lineRead = scanner.nextLine();			
			substrings = pageContent.split("\\s+");				
		}
		return substrings;
	}

	public String readPDFFile() throws Exception {
		String pdfContent=null; 
		try {
			File file = new File(pdfFileLocation);
			PDDocument document = PDDocument.load(file);
			PDFTextStripper pdfStripper = new PDFTextStripper();
			pdfContent = pdfStripper.getText(document);
			document.close();
		} catch (Exception e) {
			LogManager.logException(e, className, "Exception to read PDF file");
		}
		return pdfContent;
	}
	
	/**
	 * @Purpose: to get the PDF content in similar layout
	 * @Constraints
	 * @Input:  String pdfContent, String name of the attribute
	 * @Output: String value of the field
	 * @ Author-Date: 29/01/2019
	 * @ Reviewed-Date:
	 */
	public String readPDFFileWithLayout() throws Exception {
		String pdfContent=null; 
		try {
			RandomAccessFile file = new RandomAccessFile(new File(pdfFileLocation), "r");
			PDFParser pdfParser = new PDFParser(file);
			pdfParser.parse();
			PDDocument document = new PDDocument(pdfParser.getDocument());
			PDFTextStripper pdfStripper = new PDFLayoutTextStripper();
			pdfContent = pdfStripper.getText(document);
			document.close();
			file.close();
		} catch (Exception e) {
			LogManager.logException(e, className, "Exception to read PDF file");
		}
		return pdfContent;
	}
	
}	