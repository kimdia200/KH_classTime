package kh.java.gui.swing.component;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import kh.java.gui.util.MyUtil;


/**
 * JTable(TableModel)
 * 	- TableModel 데이터가 저장되는 객체, 이벤트 핸들링
 * JScrollPane(JTable)을 추가
 * 	- 헤더부 노출, 스크롤기능 이용
 *
 */
public class JTableTest extends JFrame {
	
	List<Member> list = new ArrayList<>();
	{	
		list.add(new Member("홍길동","서울",24,false));
		list.add(new Member("신사임당","부산",48,true));
		list.add(new Member("세종","서울",67,true));
	}
	
	public static void main(String[] args) {
		new JTableTest(500,200,"JTable").setVisible(true);
	}
	
	public JTableTest(int width, int height, String title) {
		MyUtil.init(this, width, height, title);
		
		//컬럼정보
		Object[] columnNames = {
			"이름", "주소", "나이", "결혼여부"	
		};
		
		
		//행정보(data) 2차원 배열로 나타냄
//		Object[][] rowData = {
//				{"홍길동","서울",24,false},
//				{"신사임당","부산",48,true},
//				{"세종","서울",67,true}
//		};
		//행정보 컬렉션 사용시
		Object[][] rowData = new Object[list.size()][columnNames.length];
		//인덱스를 사용하기위해 일반for문 사용
		for (int i = 0; i < list.size(); i++) {
			Member m = list.get(i);
			rowData[i][0]=m.getName();
			rowData[i][1]=m.getAddr();
			rowData[i][2]=m.getAge();
			rowData[i][3]=m.isMarried();
			
		}
		
		
		
		//테이블 생성
		JTable table = new JTable(rowData, columnNames);
		
		//헤더 클릭시 정렬기능
		table.setAutoCreateRowSorter(true);
		
		//viewport 지정
		table.setPreferredScrollableViewportSize(new Dimension(500,100));
		
		//셀 편집시 이벤트 처리하기
		TableModel model = table.getModel();
		model.addTableModelListener(new TableModelListener() {
			
			//몇행 몇열의 데이터가 변경되었는지 알아보기
            //꼭 변경되지 않아도, 포커스를가졌다가 잃을때, 콘솔창에 행,열 정보(몇행 몇행이 바뀌었는지)가 출력됨
			@Override
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				int col = e.getColumn();
//				System.out.println(row+", "+col);
				//실제 값의 변경을 알아보기
                //꼭 변경되지 않아도, 포커스를가졌다가 잃을때, 콘솔창에 바뀐 데이터가 출력됨
				Object data = model.getValueAt(row, col);
				System.out.println(data);
			}
		});
				
		//스크롤페인에 추가
		JScrollPane scrollPane = new JScrollPane(table);
		
		
		//하단 저장 버튼추가
		JButton btn = new JButton("저장");
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					//실제 전체 길이
					int rowLen = model.getRowCount();
					//실제 컬럼 길이
					int colLen = model.getColumnCount();
					
					List<Member> newList = new ArrayList<Member>();
					
					for (int i = 0; i < rowLen; i++) {
						Member newMember = new Member();
						for (int j = 0; j < colLen; j++) {
							Object data = model.getValueAt(i, j);
	//						System.out.println(data);
							//0열 = 이름, 1열 = 주소, 2열 = 나이, 3열 = 결혼여부
							switch(j) {
							case 0 : newMember.setName((String)data);break;
							case 1 : newMember.setAddr((String)data);break;
							case 2 : newMember.setAge(Integer.parseInt(data.toString()));break;
							case 3 : newMember.setMarried((boolean)data);break;
							
							}
						}
						newList.add(newMember);
					}
					for(Member newMember : newList)
						System.out.println(newMember);
				}
		});
		add(scrollPane);
		add(btn, BorderLayout.SOUTH);
	}
}
