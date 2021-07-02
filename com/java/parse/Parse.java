package com.java.parse;
 

public class Parse 
{

	


    

	/*public static void main(String[] args)
	{
		System.out.println(Parse.ISOParsing("0109", "144028", "005860020144cb02003020058020c0000c0000000000000011000001230021020100274660625542000010d220212170103432303137383836323030303030303035303030353038000830312020202020200006303030313233"));        
	}
    */
	/*
	public static void main1(String[] args)
	{
		// ISO Data
		String ISOMsg = "005860020144cb02003020058020c0000c0000000000000011000001230021020100274660625542000010d220212170103432303137383836323030303030303035303030353038000830312020202020200006303030313233";
		String ISOMessageLength = ISOMsg.substring(0,4);
		String TPDUID = ISOMsg.substring(4,4+2);
		String TPDUDestAddr = ISOMsg.substring(6,6+4);
		String TPDUOrigAddr = ISOMsg.substring(10,10+4);
		String ISO8583Message = ISOMsg.substring(14);		
		
		// Parsing ISO Message
		String[] DE;		
		ISO8583 obj = new ISO8583();
		DE = obj.Parse(ISO8583Message);
		
		// -------------Fetching Feilds ---------------------
		
		// card number
		// to get Card No we have to check card no is  manually entered or swape
		String cardNumber = "";
		String cardExpiry = "";
		String track2Data = "";
		String posEntryMode = DE[22];
		if(posEntryMode.substring(1,3).equals("01"))
		{
			// i-e card no is entered manually
			cardNumber = DE[02];
			cardExpiry = DE[14];
		}
		else
		{
			// i-e card is swape
			track2Data = DE[35];
			int pos = track2Data.lastIndexOf("d");
			cardNumber =  track2Data.substring(0, pos);
			cardExpiry = track2Data.substring(pos+1, pos+1+4);
		}
		
		String processingCode = DE[03];
		String amount = DE[04];
		String systemTraceNo = DE[11];
		String NII = DE[24];
		String ternminalID = DE[41];
		
		
		// ===== Making Response ==================
		String responseMessageLength = "003e";
		String responseTPDUID = TPDUID;
		String responseTPDUDestAddr = TPDUOrigAddr;
		String responseTPDUOrigAddr = TPDUDestAddr;
		String responseMTI = "0210";
		String responseBITMAP = "303801000e800000";
		String responseprocessingCode = processingCode;
		String responseamount = amount;
		String responsesystemTraceNo = systemTraceNo;
		String responseTime = "144028"; // todo: set parameter
		String responseTimemmss = responseTime.substring(2);
		String responseDate = "0109"; // todo: set parameter
		String responseNII = NII;
		String seq = "01"; // todo: change accordingly
		String responseRRNHex = responseDate.concat(responseTime).concat(seq);
		String responseRRNAscii = asciiToHex(responseRRNHex); // convert hex into ascii
		String responseAuthIDHex = seq.concat(responseTimemmss);
		String responseAuthIDAscii = asciiToHex(responseAuthIDHex);
		String responseResponseCode = "3030";
		String responseternminalID  = ternminalID;
		 
		
		String RESPONSE = responseMessageLength.concat(responseTPDUID).concat(responseTPDUDestAddr).concat(responseTPDUOrigAddr).concat(responseMTI).concat(responseBITMAP).concat(responseprocessingCode).concat(responseamount).concat(responsesystemTraceNo).concat(responseTime).concat(responseDate).concat(responseNII).concat(responseRRNAscii).concat(responseAuthIDAscii).concat(responseResponseCode).concat(responseternminalID);                                       
		
		
		System.out.println(DE);  
	}
	*/
	public static String ISOParsing(String InputDate, String InputTime, String ISO8583Data)
	{
		// ISO Data
		String ISOMsg = ISO8583Data;
		ISOMsg = ISOMsg.substring(2,ISOMsg.length()-1);
		String ISOMessageLength = ISOMsg.substring(0,4);
		String TPDUID = ISOMsg.substring(4,4+2);
		String TPDUDestAddr = ISOMsg.substring(6,6+4);
		String TPDUOrigAddr = ISOMsg.substring(10,10+4);
		String ISO8583Message = ISOMsg.substring(14);		
		
		// Parsing ISO Message
		String[] DE;		
		ISO8583 obj = new ISO8583();
		DE = obj.Parse(ISO8583Message);
		
		// -------------Fetching Feilds ---------------------
		
		// card number
		// to get Card No we have to check card no is  manually entered or swape
		String cardNumber = "";
		String cardExpiry = "";
		String track2Data = "";
		String posEntryMode = DE[22];
		if(posEntryMode.substring(1,3).equals("01"))
		{
			// i-e card no is entered manually
			cardNumber = DE[02];
			cardExpiry = DE[14];
		}
		else
		{
			// i-e card is swape
			track2Data = DE[35];
			int pos = track2Data.lastIndexOf("d");
			cardNumber =  track2Data.substring(0, pos);
			cardExpiry = track2Data.substring(pos+1, pos+1+4);
		}
		
		String processingCode = DE[03];
		String amount = DE[04];
		String systemTraceNo = DE[11];
		String NII = DE[24];
		String ternminalID = DE[41];
		
		
		// ===== Making Response ==================
		String responseMessageLength = "003e";
		String responseTPDUID = TPDUID;
		String responseTPDUDestAddr = TPDUOrigAddr;
		String responseTPDUOrigAddr = TPDUDestAddr;
		String responseMTI = "0210";
		String responseBITMAP = "303801000e800000";
		String responseprocessingCode = processingCode;
		String responseamount = amount;
		String responsesystemTraceNo = systemTraceNo;
		String responseTime = InputTime; // todo: set parameter
		String responseTimemmss = responseTime.substring(2);
		String responseDate = InputDate; // todo: set parameter
		String responseNII = NII;
		String seq = "01"; // todo: change accordingly
		String responseRRNHex = responseDate.concat(responseTime).concat(seq);
		String responseRRNAscii = asciiToHex(responseRRNHex); // convert hex into ascii
		String responseAuthIDHex = seq.concat(responseTimemmss);
		String responseAuthIDAscii = asciiToHex(responseAuthIDHex);
		String responseResponseCode = "3030";
		String responseternminalID  = ternminalID;
		 
		
		String RESPONSE = responseMessageLength.concat(responseTPDUID).concat(responseTPDUDestAddr).concat(responseTPDUOrigAddr).concat(responseMTI).concat(responseBITMAP).concat(responseprocessingCode).concat(responseamount).concat(responsesystemTraceNo).concat(responseTime).concat(responseDate).concat(responseNII).concat(responseRRNAscii).concat(responseAuthIDAscii).concat(responseResponseCode).concat(responseternminalID);                                       
		
		return  RESPONSE;
		//System.out.println(DE);  
	}	
	
	public static String HexToDec(String hex)
	{
		return String.valueOf(Integer.parseInt(hex,16));
	}
	public static String hexToAscii(String hexStr)
	{
	    StringBuilder output = new StringBuilder("");	     
	    for (int i = 0; i < hexStr.length(); i += 2) {
	        String str = hexStr.substring(i, i + 2);
	        output.append((char) Integer.parseInt(str, 16));
	    }	     
	    return output.toString();
	}
	public static String asciiToHex(String asciiStr)
	{
	    char[] chars = asciiStr.toCharArray();
	    StringBuilder hex = new StringBuilder();
	    for (char ch : chars) {
	        hex.append(Integer.toHexString((int) ch));
	    }	 
	    return hex.toString();
	}
	
}

 
 