package com.fuwu.sevlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class upload
 */
@WebServlet("/upload")
@MultipartConfig
public class upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		session.setAttribute("page",1);
		request.getSession().setAttribute("list2", null);
		// TODO Auto-generated method stub
		//˵�������������Ϣ����UTF-8���뷽ʽ
		request.setCharacterEncoding("utf-8");
		//Servlet3.0��������ķ�������������multipart/form-data���ͱ���ı�
		Part part = request.getPart("file");
		//System.out.println(part);
		//��ȡHTTPͷ��Ϣ
		String headerInfo = part.getHeader("content-disposition");
		//��HTTPͷ��Ϣ�л�ȡ�ļ���
		String fileName = headerInfo.substring(headerInfo.lastIndexOf("filename=\"")+10,headerInfo.length()-1);
		//��ô洢�ϴ��ļ����ļ���·��
		String fileSavingFolder = this.getServletContext().getRealPath("/UpLoad_3.0");
		//��ô洢�ϴ��ļ�������·�����ļ���·��+�ļ��������ļ���λ�ù̶����ļ����������ϴ��ļ���ԭʼ������ͬ��
		String fileSavingPath = fileSavingFolder  + File.separator +fileName;
		//����洢�ϴ��ļ����ļ��в����ڣ��򴴽��ļ���
		File f = new File(fileSavingFolder + File.separator);
		if (!f.exists()) {
		    f.mkdirs();
		}
		//���ϴ����ļ�����д��������ļ���
		part.write(fileSavingPath);
		request.getSession().setAttribute("file", fileSavingPath);
		//response.sendRedirect("/tip3.jsp");
		request.getRequestDispatcher("/tip3.jsp").forward(request, response);
	}

}
