<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="br.com.acme.business.*"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.List"%>
<%@page import="br.com.acme.model.*" %>
<%@page import="br.com.acme.dao.*" %>

<%@ taglib prefix="mt" tagdir="/WEB-INF/tags"%>

<mt:admin_tamplate title="Dashboard">
	
	
	<jsp:attribute name="content">
	
		<section class="content">
		
		
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/dashboard">Home</a></li>
		    <li class="breadcrumb-item active" aria-current="page">Medico</li>
		  </ol>
		</nav>
		 
		<div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">Novo Medico</h3>
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
              </div>
            </div>
            
            <form role="form" method="POST" action="${pageContext.request.contextPath}/admin/medicos?action=list">
              <div class="box-body">
              
              	<div class="form-group col-md-12">
					<input type="hidden" name="id" value="${medico.id}" readonly="readonly" required="required" class="form-control" id="id" placeholder="Digite o nome">
				</div>
              
                <div class="form-group">
                  <label for="nome">Nome Médico</label>
                  <input type="nome" name="nome" class="form-control" value="${medico.nomeMedico}" id="nome" placeholder="Informe o nome">
                </div>
                
                <div class="form-group">
                  <label for="crm">CRM</label>
                  <input type="text" name="crm" class="form-control" value="${medico.crm}" id="crm" placeholder="Informe o crm">
                </div>
                
                <div class="form-group">
                  <label for="telefone">Telefone</label>
                  <input type="telefone" name="telefone" class="form-control" value="${medico.telefone}" id="telefone" placeholder="Informe o telefone">
                </div>
                
              </div>

              <div class="box-footer">
					<c:if test="${medico.id != null}">
						<button type="submit" class="btn btn-warning">Editar</button>
	            	</c:if>
					
					<c:if test="${medico.id == null}">
						<button name="enviar" type="submit" class="btn btn-primary">Salvar</button>&nbsp;&nbsp;&nbsp;&nbsp;
					</c:if>
					<a href="${pageContext.request.contextPath}/admin/medicos?action=list" class="btn btn-sm btn-default">Medicos</a>&nbsp;&nbsp;&nbsp;&nbsp;
              </div>
            </form>
      	</div>
			
	 </section>
  
	</jsp:attribute>
</mt:admin_tamplate>
