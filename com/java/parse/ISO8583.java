package com.java.parse;

public class ISO8583
{
    int[] DEVarLen = new int[130];  
    int[] DEFixLen = new int[130];
    
    public ISO8583()
    {
        //Initialize BitMap Var Length Indicator
        DEVarLen[2] = 2; DEVarLen[32] = 2; DEVarLen[33] = 2; DEVarLen[34] = 2; DEVarLen[35] = 2; DEVarLen[36] = 3;
        DEVarLen[44] = 2; DEVarLen[45] = 2; DEVarLen[46] = 3; DEVarLen[47] = 3; DEVarLen[48] = 3; DEVarLen[54] = 3;
        DEVarLen[55] = 3; DEVarLen[56] = 3; DEVarLen[57] = 3; DEVarLen[58] = 3; DEVarLen[59] = 3;
        DEVarLen[60] = 1; DEVarLen[61] = 3; DEVarLen[62] = 3; DEVarLen[63] = 3; DEVarLen[72] = 3; DEVarLen[99] = 2;
        DEVarLen[100] = 2; DEVarLen[102] = 2; DEVarLen[103] = 2; DEVarLen[104] = 3; DEVarLen[105] = 3;
        DEVarLen[106] = 3; DEVarLen[107] = 3; DEVarLen[108] = 3; DEVarLen[109] = 3; DEVarLen[110] = 3;
        DEVarLen[111] = 3; DEVarLen[112] = 3; DEVarLen[113] = 2; DEVarLen[114] = 3; DEVarLen[115] = 3;
        DEVarLen[116] = 3; DEVarLen[117] = 3; DEVarLen[118] = 3; DEVarLen[119] = 3; DEVarLen[120] = 3; DEVarLen[121] = 3;
        DEVarLen[122] = 3; DEVarLen[123] = 3; DEVarLen[124] = 3; DEVarLen[125] = 2; DEVarLen[126] = 1; DEVarLen[127] = 3;

        // "-" means not numeric.

        DEFixLen[0] = 16; DEFixLen[1] = 16; DEFixLen[3] = 6; DEFixLen[4] = 12;
        DEFixLen[5] = 12; DEFixLen[6] = 12; DEFixLen[7] = 10; DEFixLen[8] = 8;
        DEFixLen[9] = 8; DEFixLen[10] = 8; DEFixLen[11] = 6; DEFixLen[12] = 6;
        DEFixLen[13] = 4; DEFixLen[14] = 4; DEFixLen[15] = 4; DEFixLen[16] = 4;
        DEFixLen[17] = 4; DEFixLen[18] = 4; DEFixLen[19] = 3; DEFixLen[20] = 3;
        DEFixLen[21] = 3; DEFixLen[22] = 3; DEFixLen[23] = 3; DEFixLen[24] = 3;
        DEFixLen[25] = 2; DEFixLen[26] = 2; DEFixLen[27] = 1; DEFixLen[28] = 8;
        DEFixLen[29] = 8; DEFixLen[30] = 8; DEFixLen[31] = 8; DEFixLen[37] = -12;
        DEFixLen[38] = -6; DEFixLen[39] = -2; DEFixLen[40] = -3; DEFixLen[41] = -8;
        DEFixLen[42] = -15; DEFixLen[43] = -40; DEFixLen[49] = -3; DEFixLen[50] = -3;
        DEFixLen[51] = -3; DEFixLen[52] = -16; DEFixLen[53] = 18; DEFixLen[64] = -4;
        DEFixLen[65] = -16; DEFixLen[66] = 1; DEFixLen[67] = 2; DEFixLen[68] = 3;
        DEFixLen[69] = 3; DEFixLen[70] = 3; DEFixLen[71] = 4; DEFixLen[73] = 6;
        DEFixLen[74] = 10; DEFixLen[75] = 10; DEFixLen[76] = 10; DEFixLen[77] = 10;
        DEFixLen[78] = 10; DEFixLen[79] = 10; DEFixLen[80] = 10; DEFixLen[81] = 10;
        DEFixLen[82] = 12; DEFixLen[83] = 12; DEFixLen[84] = 12; DEFixLen[85] = 12;
        DEFixLen[86] = 15; DEFixLen[87] = 15; DEFixLen[88] = 15; DEFixLen[89] = 15;
        DEFixLen[90] = 42; DEFixLen[91] = -1; DEFixLen[92] = 2; DEFixLen[93] = 5;
        DEFixLen[94] = -7; DEFixLen[95] = -42; DEFixLen[96] = -8; DEFixLen[97] = 16;
        DEFixLen[98] = -25; DEFixLen[101] = -17; DEFixLen[128] = -16;
    }

    public String[] Parse(String ISOmsg)
    {
    	String[] DE = new String[130];
    	
    	String de1Binary = "";
    	String de2Binary = "";
        int FieldNo;


        int myPos;
        int myLenght;
        int len;
        //Get MTI
        myPos = 0;
        myLenght = 4;
        String MTI = ISOmsg.substring(myPos, myLenght);
  
        //========BM 129 is the MTI============
        FieldNo = 129;        

        DE[FieldNo] = MTI;
        //========================================
        //Get BitMap 1a
        myPos += myLenght;
        myLenght = 16; 
        DE[0] = ISOmsg.substring(myPos, myPos+myLenght);
        
        //Convert BM0 to Binary 
        de1Binary = DEtoBinary(DE[0]);
        
        //BitMap #1
        FieldNo = 1; 
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1"))
        {
            myPos += myLenght;
            myLenght = 16;
            DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght);
            de2Binary = DEtoBinary(DE[FieldNo]);
        }
        
        //------------BM2--------------
        FieldNo = 2;
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1"))
        {
            myPos += myLenght; 
            myLenght = Integer.parseInt(ISOmsg.substring(myPos, myPos+2));
            
            myPos += 2;
            DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght);
        }
        
        //------------BM3--------------
        FieldNo = 3;
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1"))
        { 
        	myPos += myLenght; myLenght = 6; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght);
        }
        
        //------------BM4--------------
        FieldNo = 4;
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1") ) { myPos += myLenght; myLenght = 12; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //------------BM5--------------
        FieldNo = 5;
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 12; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //------------BM6--------------
        FieldNo = 6;        
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 12; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //------------BM7--------------
        FieldNo = 7;        
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 10; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //------------BM8--------------
        FieldNo = 8;        
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 8; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //------------BM9--------------
        FieldNo = 9;       
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 8; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }

        //------------BM 10--------------
        FieldNo = 10;        
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 8; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //------------BM 11--------------
        FieldNo = 11;
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 6; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
         
        //------------BM 12--------------
        FieldNo = 12;
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 6; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }

        //------------BM 13--------------
        FieldNo = 13;
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 4; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }

        //------------BM 14--------------
        FieldNo = 14;
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 4; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }

        //------------BM 15--------------
        FieldNo = 15;
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 4; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //------------BM 16--------------
        FieldNo = 16;
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 4; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //------------BM 17--------------
        FieldNo = 17;
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 4; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }

        //------------BM 18--------------
        FieldNo = 18;
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 4; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //------------BM 19--------------
        FieldNo = 19;
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 4; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //------------BM 20--------------
        FieldNo = 20;
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 4; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //------------BM 21--------------
        FieldNo = 21;
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 4; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //------------BM 22--------------
        FieldNo = 22;
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 4; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //------------BM 23--------------
        FieldNo = 23;
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 4; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //------------BM 24--------------
        FieldNo = 24;
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 4; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //------------BM 25--------------
        FieldNo = 25;
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 2; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //------------BM 26--------------
        FieldNo = 26;
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 2; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //------------BM 27--------------
        FieldNo = 27;
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 2; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }

        //------------BM 28--------------
        FieldNo = 28;
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght =8 ; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //------------BM 29--------------
        FieldNo = 29;
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 8; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //------------BM 30--------------
        FieldNo = 30;
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 8; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //------------BM 31--------------
        FieldNo = 31;
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 8; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //-----------32---------------
        FieldNo = 32;        
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1"))
        {
            myPos += myLenght;
            myLenght = Integer.parseInt(ISOmsg.substring(myPos, myPos+2));
            myPos += 2;
            DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght);
        }
        
        //------------33--------------
        FieldNo = 33;        
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1"))
        {
            myPos += myLenght;
            myLenght = Integer.parseInt(ISOmsg.substring(myPos, myPos+2));
            myPos += 2;
            DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght);
        }
        
        //-----------34---------------
        FieldNo = 34;        
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1"))
        {
            myPos += myLenght;
            myLenght = Integer.parseInt(ISOmsg.substring(myPos, 2));
            myPos += 2;
            DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght);
        }
        
        //---------35-----------------
        FieldNo = 35;        
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1"))
        {
            myPos += myLenght; len = 2;
            myLenght = Integer.parseInt(ISOmsg.substring(myPos, myPos+len));
            if(myLenght % 2 == 0)
            {
            	
            }
            else
            {
            	myLenght = myLenght + 1;
            }
            	
            myPos += len;
            DE[FieldNo] = ISOmsg.substring(myPos,myPos+ myLenght);
        }
        
        //----------36----------------
        FieldNo = 36;        
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1"))
        {
            myPos += myLenght; len = 4;
            myLenght = Integer.parseInt(ISOmsg.substring(myPos, myPos+len));
            myPos += len;
            DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght);
        }
        
        //----------37----------------
        FieldNo = 37;        
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 24; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //---------38-----------------
        FieldNo = 38;        
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 12; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //----------39----------------
        FieldNo = 39;        
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 4; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //----------40----------------
        FieldNo = 40;        
        if (de1Binary.substring(FieldNo - 1, (FieldNo - 1)+1).equals("1")) { myPos += myLenght; myLenght = 4; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //----------41----------------
        FieldNo = 41;
        if (de1Binary.substring(FieldNo - 1,(FieldNo - 1)+ 1).equals("1")) { myPos += myLenght; myLenght = 16; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }

        //----------42----------------
        FieldNo = 42;
        if (de1Binary.substring(FieldNo - 1,(FieldNo - 1)+ 1).equals("1")) { myPos += myLenght; myLenght = 30; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }

        //---------43-----------------
        FieldNo = 43;        
        if (de1Binary.substring(FieldNo - 1,(FieldNo - 1)+ 1).equals("1")) { myPos += myLenght; myLenght = 40; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //---------44-----------------
        FieldNo = 44;
        if (de1Binary.substring(FieldNo - 1,(FieldNo - 1)+ 1).equals("1"))
        {
            myPos += myLenght; len = 2;
            myLenght = Integer.parseInt(ISOmsg.substring(myPos, myPos+len));
            myPos += len;
            DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght);
        }

        //----------45----------------
        FieldNo = 45; 
        if (de1Binary.substring(FieldNo - 1,(FieldNo - 1)+ 1).equals("1"))
        {
            myPos += myLenght; len = 2;
            myLenght = Integer.parseInt(ISOmsg.substring(myPos, myPos+len));
            myPos += len;
            DE[FieldNo] = ISOmsg.substring(myPos, myLenght);
        }
        
        //----------46----------------
        FieldNo = 46;
        if (de1Binary.substring(FieldNo - 1,(FieldNo - 1)+ 1).equals("1"))
        {
            myPos += myLenght; len = 3;
            myLenght = Integer.parseInt(ISOmsg.substring(myPos, myPos+len));
            myPos += len;
            DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght);
        }
        
        //-----------47---------------
        FieldNo = 47;        
        if (de1Binary.substring(FieldNo - 1,(FieldNo - 1)+ 1).equals("1"))
        {
            myPos += myLenght; len = 3;
            myLenght = Integer.parseInt(ISOmsg.substring(myPos, myPos+len));
            myPos += len;
            DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght);
        }
        
        //---------48-----------------
        FieldNo = 48;
        if (de1Binary.substring(FieldNo - 1,(FieldNo - 1)+ 1).equals("1"))
        {
            myPos += myLenght; len = 3;
            myLenght = Integer.parseInt(ISOmsg.substring(myPos, len));
            myPos += len;
            DE[FieldNo] = ISOmsg.substring(myPos, myLenght);
        }
        
        //---------49-----------------
        FieldNo = 49;        
        if (de1Binary.substring(FieldNo - 1,(FieldNo - 1)+ 1).equals("1")) { myPos += myLenght; myLenght = 4; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //---------50-----------------
        FieldNo = 50;        
        if (de1Binary.substring(FieldNo - 1,(FieldNo - 1)+ 1).equals("1")) { myPos += myLenght; myLenght = 4; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //---------51-----------------
        FieldNo = 51;        
        if (de1Binary.substring(FieldNo - 1,(FieldNo - 1)+ 1).equals("1")) { myPos += myLenght; myLenght = 4; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //---------52-----------------
        FieldNo = 52;        
        if (de1Binary.substring(FieldNo - 1,(FieldNo - 1)+ 1).equals("1")) { myPos += myLenght; myLenght = 16; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //---------53-----------------
        FieldNo = 53;        
        if (de1Binary.substring(FieldNo - 1,(FieldNo - 1)+ 1).equals("1")) { myPos += myLenght; myLenght = 18; DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght); }
        
        //---------54-----------------
        FieldNo = 54; 
        if (de1Binary.substring(FieldNo - 1,(FieldNo - 1)+ 1).equals("1"))
        {
            myPos += myLenght; len = 3;
            myLenght = Integer.parseInt(ISOmsg.substring(myPos, myPos+len)); myPos += len;
            DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght);
        }
        
        //----------55---------
        FieldNo = 55;
        if (de1Binary.substring(FieldNo - 1,(FieldNo - 1)+ 1).equals("1"))
        {
            myPos += myLenght; len = 3;
            myLenght = Integer.parseInt(ISOmsg.substring(myPos, myPos+len)); myPos += len;
            DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght);
        }
        
        //----------56---------
        FieldNo = 56;
        if (de1Binary.substring(FieldNo - 1,(FieldNo - 1)+ 1).equals("1"))
        {
            myPos += myLenght; len = 3;
            myLenght = Integer.parseInt(ISOmsg.substring(myPos, myPos+len)); myPos += len;
            DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght);
        }
        
        //----------57---------
        FieldNo = 57;
        if (de1Binary.substring(FieldNo - 1,(FieldNo - 1)+ 1).equals("1"))
        {
            myPos += myLenght; len = 3;
            myLenght = Integer.parseInt(ISOmsg.substring(myPos, myPos+len)); myPos += len;
            DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght);
        }
        
        //----------58---------
        FieldNo = 58;
        if (de1Binary.substring(FieldNo - 1,(FieldNo - 1)+ 1).equals("1"))
        {
            myPos += myLenght; len = 3;
            myLenght = Integer.parseInt(ISOmsg.substring(myPos, myPos+len)); myPos += len;
            DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght);
        }
        
        //----------59---------
        FieldNo = 59;
        if (de1Binary.substring(FieldNo - 1,(FieldNo - 1)+ 1).equals("1"))
        {
            myPos += myLenght; len = 3;
            myLenght = Integer.parseInt(ISOmsg.substring(myPos, myPos+len)); myPos += len;
            DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght);
        }
        
        //----------60---------
        FieldNo = 60;
        if (de1Binary.substring(FieldNo - 1,(FieldNo - 1)+ 1).equals("1"))
        {
            myPos += myLenght; len = 1;
            myLenght = Integer.parseInt(ISOmsg.substring(myPos, myPos+len)); myPos += len;
            DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght);
        }
        
        //----------61---------
        FieldNo = 61;
        if (de1Binary.substring(FieldNo - 1,(FieldNo - 1)+ 1).equals("1"))
        {
            try
            {
                myPos += myLenght; len = 3;
                myLenght = Integer.parseInt(ISOmsg.substring(myPos, myPos+len)); myPos += len;
                DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght);
            }
            catch (Exception ex)
            {
                DE[FieldNo] = "xxxx";
            }
        }
        
        //----------62---------
        FieldNo = 62;
        if (de1Binary.substring(FieldNo - 1,(FieldNo - 1)+ 1).equals("1"))
        {
            try
            {
                myPos += myLenght; len = 3;
                myLenght = Integer.parseInt(ISOmsg.substring(myPos, myPos+len)); myPos += len;
                DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght);
            }
            catch (Exception ex)
            {
                DE[FieldNo] = "xxxx";
            }
        }
        
        //----------63---------
        FieldNo = 63;
        if (de1Binary.substring(FieldNo - 1,(FieldNo - 1)+ 1).equals("1"))
        {
            try
            {
                myPos += myLenght; len = 3;
                myLenght = Integer.parseInt(ISOmsg.substring(myPos, myPos+len)); myPos += len;
                DE[FieldNo] = ISOmsg.substring(myPos, myPos+myLenght);
            }
            catch (Exception ex)
            {
                DE[FieldNo] = "xxxx";
            }
        }
        
        
         
        
        // ---------- return ---------
    	return DE; 
    }
    
    private String DEtoBinary(String HexDE)
    {
    	String deBinary = "";
        for (int I = 0; I <= 15; I++)
        {
            deBinary = deBinary + Hex2Binary(HexDE.substring(I, 1 + I));
        }
        return deBinary;
    }

    private String Hex2Binary(String DE)
    {
    	String myBinary = "";
        switch (DE)
        {
            case "0":
                myBinary = "0000";
                break;
            case "1":
                myBinary = "0001";
                break;
            case "2":
                myBinary = "0010";
                break;
            case "3":
                myBinary = "0011";
                break;
            case "4":
                myBinary = "0100";
                break;
            case "5":
                myBinary = "0101";
                break;
            case "6":
                myBinary = "0110";
                break;
            case "7":
                myBinary = "0111";
                break;
            case "8":
                myBinary = "1000";
                break;
            case "9":
                myBinary = "1001";
                break;
            case "A":
            case "a":
                myBinary = "1010";
                break;
            case "B":
            case "b":
                myBinary = "1011";
                break;
            case "C":
            case "c":
                myBinary = "1100";
                break;
            case "D":
            case "d":
                myBinary = "1101";
                break;
            case "E":
            case "e":
                myBinary = "1110";
                break;
            case "F":
            case "f":
                myBinary = "1111";
                break;
        }
        return myBinary;
    }

    

}
