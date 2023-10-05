package com.gooroomee.adapter.dto.io;

public class IfMcCs012_I {
	/**
	 * 데이터 헤더
	 */
	private DataHeader dataHeader;
	
	/**
	 * 데이터 바디
	 */
	private DataBody dataBody;
	
	public DataHeader getDataHeader() {
		return dataHeader;
	}

	public void setDataHeader(DataHeader dataHeader) {
		this.dataHeader = dataHeader;
	}

	public DataBody getDataBody() {
		return dataBody;
	}

	public void setDataBody(DataBody dataBody) {
		this.dataBody = dataBody;
	}
	
	@Override
	public String toString() {
		return "IfMcCs012_I [dataHeader=" + dataHeader + ", dataBody=" + dataBody + "]";
	}



	/**
	 * 데이터 헤더
	 * @author T440s
	 *
	 */
	public static class DataHeader {
		/**
		 * 서비스 ID
		 */
		private String SRVC_ID;
		
		/**
		 * 화면 ID
		 */
		private String SCRN_ID;
		
		/**
		 * 처리 코드
		 */
		private String CRTF_RTCD;
		
		/**
		 * 처리 결과 메세지
		 */
		private String DLRE_MSG;
		
		/**
		 * 기관 코드
		 */
		private String ORGN_CODE;
		
		/**
		 * 사용자 ID
		 */
		private String USER_ID;

		public String getSRVC_ID() {
			return SRVC_ID;
		}

		public void setSRVC_ID(String sRVC_ID) {
			SRVC_ID = sRVC_ID;
		}

		public String getSCRN_ID() {
			return SCRN_ID;
		}

		public void setSCRN_ID(String sCRN_ID) {
			SCRN_ID = sCRN_ID;
		}

		public String getCRTF_RTCD() {
			return CRTF_RTCD;
		}

		public void setCRTF_RTCD(String cRTF_RTCD) {
			CRTF_RTCD = cRTF_RTCD;
		}

		public String getDLRE_MSG() {
			return DLRE_MSG;
		}

		public void setDLRE_MSG(String dLRE_MSG) {
			DLRE_MSG = dLRE_MSG;
		}

		public String getORGN_CODE() {
			return ORGN_CODE;
		}

		public void setORGN_CODE(String oRGN_CODE) {
			ORGN_CODE = oRGN_CODE;
		}

		public String getUSER_ID() {
			return USER_ID;
		}

		public void setUSER_ID(String uSER_ID) {
			USER_ID = uSER_ID;
		}

		@Override
		public String toString() {
			return "DataHeader [SRVC_ID=" + SRVC_ID + ", SCRN_ID=" + SCRN_ID + ", CRTF_RTCD=" + CRTF_RTCD
					+ ", DLRE_MSG=" + DLRE_MSG + ", ORGN_CODE=" + ORGN_CODE + ", USER_ID=" + USER_ID + "]";
		}
		
	}

	/**
	 * 데이터 바디
	 * @author T440s
	 *
	 */
	public static class DataBody {
		/**
		 * 이니텍 인증 토큰
		 */
		private String initechOAuthToken;
		
		/**
		 * 트랜잭션 ID
		 */
		private String reqTxId;
		
		/**
		 * 인증서명구분
		 */
		private String op;
		
		public String getInitechOAuthToken() {
			return initechOAuthToken;
		}
		public void setInitechOAuthToken(String initechOAuthToken) {
			this.initechOAuthToken = initechOAuthToken;
		}
		public String getReqTxId() {
			return reqTxId;
		}
		public void setReqTxId(String reqTxId) {
			this.reqTxId = reqTxId;
		}
		public String getOp() {
			return op;
		}
		public void setOp(String op) {
			this.op = op;
		}
		@Override
		public String toString() {
			return "DataBody [initechOAuthToken=" + initechOAuthToken + ", reqTxId=" + reqTxId + ", op=" + op + "]";
		}
		
		
	}
}
