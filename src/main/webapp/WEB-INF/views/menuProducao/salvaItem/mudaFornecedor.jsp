<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<style>
.load-wrapp {
   
}

.load-wrapp p {padding: 0 0 20px;}
.load-wrapp:last-child {margin-right: 0;}

.line {
    display: inline-block;
    width: 10px;
    height: 10px;
    border-radius: 15px;
    background-color: #4b9cdb;
}

.ring-1 {
    width: 10px;
    height: 10px;
    margin: 0 auto;
    padding: 10px;
    border: 7px dashed #4b9cdb;
    border-radius: 100%;
}

.ring-2 {
    position: relative;
    width: 45px;
    height: 45px;
    margin: 0 auto;
    border: 4px solid #4b9cdb;
    border-radius: 100%;
}

.ball-holder {
    position: absolute;
    width: 12px;
    height: 45px;
    left: 17px;
    top: 0px;
}

.ball {
    position: absolute;
    top: -11px;
    left: 0;
    width: 16px;
    height: 16px;
    border-radius: 100%;
    background: #4282B3;
}

.letter-holder {padding: 16px;}

.letter {
    float: left;
    font-size: 14px;
    color: #777;
}

.square {
    width: 12px;
    height: 12px;
    border-radius: 4px;
    background-color: #4b9cdb;
}

.spinner {
    position: relative;
    width: 45px;
    height: 45px;
    margin: 0 auto;
}

.bubble-1,
.bubble-2 {
    position: absolute;
    top: 0;
    width: 25px;
    height: 25px;
    border-radius: 100%;
    background-color: #4b9cdb;
}

.bubble-2 {
    top: auto;
    bottom: 0;
}

.bar {
    float: left;
    width: 15px;
    height: 6px;
    border-radius: 2px;
    background-color: #4b9cdb;
}

/* =Animate the stuff
------------------------ */
.load-1 .line:nth-last-child(1) {animation: loadingA 1.5s 1s infinite;}
.load-1 .line:nth-last-child(2) {animation: loadingA 1.5s .5s infinite;}
.load-1 .line:nth-last-child(3) {animation: loadingA 1.5s 0s infinite;}

.load-2 .line:nth-last-child(1) {animation: loadingB 1.5s 1s infinite;}
.load-2 .line:nth-last-child(2) {animation: loadingB 1.5s .5s infinite;}
.load-2 .line:nth-last-child(3) {animation: loadingB 1.5s 0s infinite;}

.load-3 .line:nth-last-child(1) {animation: loadingC .6s .1s linear infinite;}
.load-3 .line:nth-last-child(2) {animation: loadingC .6s .2s linear infinite;}
.load-3 .line:nth-last-child(3) {animation: loadingC .6s .3s linear infinite;}

.load-4 .ring-1 {animation: loadingD 1.5s .3s cubic-bezier(.17,.37,.43,.67) infinite;}

.load-5 .ball-holder {animation: loadingE 1.3s linear infinite;}

.load-6 .letter {
    animation-name: loadingF;
    animation-duration: 1.6s;
    animation-iteration-count: infinite;
    animation-direction: linear;
}

.l-1 {animation-delay: .48s;}
.l-2 {animation-delay: .6s;}
.l-3 {animation-delay: .72s;}
.l-4 {animation-delay: .84s;}
.l-5 {animation-delay: .96s;}
.l-6 {animation-delay: 1.08s;}
.l-7 {animation-delay: 1.2s;}
.l-8 {animation-delay: 1.32s;}
.l-9 {animation-delay: 1.44s;}
.l-10 {animation-delay: 1.56s;}

.load-7 .square {animation: loadingG 1.5s cubic-bezier(.17,.37,.43,.67) infinite;}

.load-8 .line {animation: loadingH 1.5s cubic-bezier(.17,.37,.43,.67) infinite;}

.load-9 .spinner {animation: loadingI 2s linear infinite;}
.load-9 .bubble-1, .load-9 .bubble-2 {animation: bounce 2s ease-in-out infinite;}
.load-9 .bubble-2 {animation-delay: -1.0s;}

.load-10 .bar {animation: loadingJ 2s cubic-bezier(.17,.37,.43,.67) infinite;}

@keyframes loadingA {
    0 {height: 15px;}
    50% {height: 35px;}
    100% {height: 15px;}
}

@keyframes loadingB {
    0 {width: 15px;}
    50% {width: 35px;}
    100% {width: 15px;}
}

@keyframes loadingC {
    0 {transform: translate(0,0);}
    50% {transform: translate(0,15px);}
    100% {transform: translate(0,0);}
}

@keyframes loadingD {
    0 {transform: rotate(0deg);}
    50% {transform: rotate(180deg);}
    100% {transform: rotate(360deg);}
}

@keyframes loadingE {
    0 {transform: rotate(0deg);}
    100% {transform: rotate(360deg);}
}

@keyframes loadingF {
    0% {opacity: 0;}
    100% {opacity: 1;}
}

@keyframes loadingG {
    0% {transform: translate(0,0) rotate(0deg);}
    50% {transform: translate(70px,0) rotate(360deg);}
    100% {transform: translate(0,0) rotate(0deg);}
}

@keyframes loadingH {
    0% {width: 15px;}
    50% {width: 35px; padding: 4px;}
    100% {width: 15px;}
}

@keyframes loadingI {
    100% {transform: rotate(360deg);}
}

@keyframes bounce  {
  0%, 100% {transform: scale(0.0);}
  50% {transform: scale(1.0);}
}

@keyframes loadingJ {
  0%,100% {transform: translate(0,0);}
  
  50% {
      transform: translate(80px,0);
      background-color: #f5634a;
      width: 25px;
  }
}

</style>


<div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"
                style="margin-top: -12px;">
                  <span aria-hidden="true" style="font-size: 50px;font-family: 'OpenSansLight';font-weight: normal;">
                    &times;
                  </span>
                </button>
                <h4 class="modal-title" id="myModalLabel" style="font-family: 'OpenSansLight'">
                  Trocar de Fornecedor
                </h4>
              </div>
              <div class="modal-body">
                <div class="row">
	                     <div class="col-md-12 form-inline"
	                     style="height: 117px; border: 1px solid #ddd; padding: 20px 20px 20px 10px; margin-bottom: 10px; border-radius: 5px;">
	                       <p>
	                         Trocar de Fornecedor
	                       </p>
	                       <select class="form-control input-180px" id="selectFornecedor">
	                                        
	                          <c:forEach items="${fornecedoresLista}" var="fornecedoresLista">
	                            <option value="${fornecedoresLista[0]}">
	                              ${fornecedoresLista[1]}
	                            </option>
	                          </c:forEach>
	                          
	                       </select>
	                       &nbsp&nbsp&nbsp
	                       <a class="btn btn-danger" onclick="trocarFornecedor(${idProdutoGrupo});">Trocar Agora</a>
							
							<div class="load-wrapp display-none" id="loadFornecedor">
								<div class="load-3">
									<div class="line"></div>
									<div class="line"></div>
									<div class="line"></div>
								</div>
							</div>
							
	                     </div>
                    </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                  Close
                </button>
              </div>
            </div>
</div>