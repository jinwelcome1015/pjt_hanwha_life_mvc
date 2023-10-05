package com.gooroomee.adapter.dto.io;

public class IfMcCs012_O {
	/**
	 * 데이터 헤더
	 */
	private DataHeader dataHeader;

	/**
	 * 데이터 바디
	 */
	private DataBody dataBody;

	/**
	 * 데이터 헤더
	 * 
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

		@Override
		public String toString() {
			return "DataHeader [SRVC_ID=" + SRVC_ID + ", SCRN_ID=" + SCRN_ID + ", CRTF_RTCD=" + CRTF_RTCD
					+ ", DLRE_MSG=" + DLRE_MSG + ", getSRVC_ID()=" + getSRVC_ID() + ", getSCRN_ID()=" + getSCRN_ID()
					+ ", getCRTF_RTCD()=" + getCRTF_RTCD() + ", getDLRE_MSG()=" + getDLRE_MSG() + ", getClass()="
					+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
		}
	}

	/**
	 * 데이터 바디
	 * 
	 * @author T440s
	 *
	 */
	public static class DataBody {
		/**
		 * 응답 코드
		 */
		private String resCode;

		/**
		 * 오류 메시지
		 */
		private String errorMessage;

		/**
		 * 인증 진행 상태 코드
		 */
		private String status;

		/**
		 * 페이로드
		 */
		private Payload payload;

		public String getResCode() {
			return resCode;
		}

		public void setResCode(String resCode) {
			this.resCode = resCode;
		}

		public String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Payload getPayload() {
			return payload;
		}

		public void setPayload(Payload payload) {
			this.payload = payload;
		}

		@Override
		public String toString() {
			return "DataBody [resCode=" + resCode + ", errorMessage=" + errorMessage + ", status=" + status
					+ ", payload=" + payload + "]";
		}

		/**
		 * 페이로드
		 * 
		 * @author T440s
		 *
		 */
		public static class Payload {
			/**
			 * 인증 서명 구분
			 */
			private String op;

			/**
			 * 인증 서비스 ID
			 */
			private String pid;

			/**
			 * 사용자 이름
			 */
			private String uname;

			/**
			 * 사용자 생년월일
			 */
			private String ubirthday;

			/**
			 * 사용자 성별
			 */
			private String ugender;

			/**
			 * 사용자 휴대폰 번호
			 */
			private String uphone;

			/**
			 * 사용자 CI
			 */
			private String uci;

			/**
			 * 전자 서명 데이터
			 */
			private String signedData;

			public String getOp() {
				return op;
			}

			public void setOp(String op) {
				this.op = op;
			}

			public String getPid() {
				return pid;
			}

			public void setPid(String pid) {
				this.pid = pid;
			}

			public String getUname() {
				return uname;
			}

			public void setUname(String uname) {
				this.uname = uname;
			}

			public String getUbirthday() {
				return ubirthday;
			}

			public void setUbirthday(String ubirthday) {
				this.ubirthday = ubirthday;
			}

			public String getUgender() {
				return ugender;
			}

			public void setUgender(String ugender) {
				this.ugender = ugender;
			}

			public String getUphone() {
				return uphone;
			}

			public void setUphone(String uphone) {
				this.uphone = uphone;
			}

			public String getUci() {
				return uci;
			}

			public void setUci(String uci) {
				this.uci = uci;
			}

			public String getSignedData() {
				return signedData;
			}

			public void setSignedData(String signedData) {
				this.signedData = signedData;
			}

			@Override
			public String toString() {
				return "Payload [op=" + op + ", pid=" + pid + ", uname=" + uname + ", ubirthday=" + ubirthday
						+ ", ugender=" + ugender + ", uphone=" + uphone + ", uci=" + uci + ", signedData=" + signedData
						+ "]";
			}
		}
	}	
}
