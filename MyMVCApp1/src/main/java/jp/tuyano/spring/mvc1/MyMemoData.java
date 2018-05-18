package jp.tuyano.spring.mvc1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MyMemoData {
	private static final long serialVersionUID = 1L;
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Column(nullable = false)
		private String title;
		
		@Column(nullable = false)
		private String content;
		
		public MyMemoData() {
			super();
		}
		public MyMemoData(String title, String content) {
			super();
			this.title = title;
			this.content = content;
		}
		
		public Long getId() {
			return id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		
		@Override
		public String toString() {
			return "MyMemoData [id=" + id + ", title=" + title + ", content="
					+ content + "]";
		}
}
