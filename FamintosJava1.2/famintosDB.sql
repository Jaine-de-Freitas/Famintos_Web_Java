create database FamintosBD;
use FamintosBD;
create table Funcionario(
	nome varchar(50) not null,
    senha varchar(15) not null,
    id_usuario double primary key not null auto_increment,
    admissao date not null,
    email varchar(50) not null,
    funcao varchar(15) not null,
    cpf varchar(14) not null,
    data_cadastro datetime not null
);
update tbl_venda set statusC='entregue' where  cd_venda in (select cd_venda from tbl_venda where no_ticket='64777afd51582');
select *from Funcionario;

insert into Funcionario(nome,senha,admissao,email,funcao,cpf,data_cadastro)
values('Usuario ADM','@adm1nFamint0s','2023-03-28','admin@gmail.com','Administrador','000.111.222-33','2023-04-08 11:06:28')
;
insert into Funcionario(nome,senha,admissao,email,funcao,cpf,data_cadastro)
values('Maria Helena','maria123','2023-03-29','malena@gmail.com','Cozinheiro','000.111.222-34','2023-04-08 08:05:00')
;

insert into Funcionario(nome,senha,admissao,email,funcao,cpf,data_cadastro)
values('Andre de Souza', 'andregarçom', '2022-04-20', 'andre@outlook.com', 'Garçom', '000.222.555-88','2019-01-12 12:01:30');
insert into Funcionario(nome,senha,admissao,email,funcao,cpf,data_cadastro)
values('Amalia Alves dos Santos Ferreira', 'amailachef1', '2017-03-20', 'amaliaalv@hotmail.com', 'Chef', '432.394.280-70','2005-10-12 23:59:14');
insert into Funcionario(nome,senha,admissao,email,funcao,cpf,data_cadastro)
values('Caio Fernandes', 'caiogarc', '2023-04-01', 'fercaio@outlook.com', 'Garçom', '000.234.319-18','2017-12-12 14:30:00');

insert into Funcionario(nome,senha,admissao,email,funcao,cpf,data_cadastro)
values('Antônio Carlos Junior','tonyjunior','2023-05-12','tony@outlook.com','Cozinheiro','123.444.567-12','2023-04-08 11:53:58')
;

select *from Funcionario order by data_cadastro desc;

create table Produtos(
	id_produto double primary key not null auto_increment,
	produto varchar(50) not null,
    categoria varchar(15) not null,
    lote varchar(12) not null,
    vencimento date not null,
    quantidade integer not null,
    valor varchar(15) not null,
    data_cadastro datetime not null
);
select *from Funcionario where funcao like '%c%';
select *from Produtos;

insert into Produtos (produto,categoria,lote,vencimento,quantidade,valor,data_cadastro) values('Farinha Branca 500g - Dona Benta','Secos','VEJ4Q3','2025-09-24','12','5.00','2023-04-08 11:06:28');
insert into Produtos (produto,categoria,lote,vencimento,quantidade,valor,data_cadastro) values('Carne Seca','Carnes e Frios','W4543GH','2022-10-20','5','50.00','2022-10-18 15:06:40');
insert into Produtos (produto,categoria,lote,vencimento,quantidade,valor,data_cadastro) values('Tampico 250ml','Bebidas','VN25B7','2020-03-09','10','2.50','2023-04-10 18:06:28');
insert into Produtos (produto,categoria,lote,vencimento,quantidade,valor,data_cadastro) values('Maionese Hellmann´s','Molhos','9NV537TVCV3F','2023-10-15','20','8.50','2023-04-08 12:06:28');
insert into Produtos (produto,categoria,lote,vencimento,quantidade,valor,data_cadastro) values('Xesperito Flocos 1L','Sorveteria','24CBV7','2025-12-27','30','12.00','2020-04-08 15:06:28');

select *from Produtos order by vencimento asc;

create table tbl_comidas(
	cd_comida int primary key auto_increment,
    nm_comida varchar(70) not null,
    vl_preco decimal (6,2) not null,
    nm_categoria varchar(50) not null,
    img_comida varchar(300) not null,
    qt_estoque int not null,
    data_cadastro datetime not null
);

insert into tbl_comidas (nm_comida, vl_preco, cd_categoria, img_comida, qt_estoque, data_cadastro)
values 
(
	'Coxinha de carne de capivara',
    '4.80',
    '1',
    'estetica_imgs\\cafe.jpg',
    '12',
    '2020-04-08 15:06:28'
);

select *from tbl_comidas;
insert into tbl_comidas(nm_comida,vl_preco,nm_categoria,img_comida,qt_estoque,data_cadastro) values('Café','2.50','Bebidas','D:\\famintOs\\estetica_imgs\\cafe.jpg','12','2020-04-08 15:06:28');



CREATE TABLE `tbl_venda` (
	  `cd_venda` int(11) primary key auto_increment,
	  `no_Ticket` varchar(13) not null,
      `nome_cliente` varchar(100) not null,
	  `cd_cliente` varchar(15) not null,
	  `cd_comida` int(11) not null,
	  `qt_comida` int(11) not null,
	  `vl_item` decimal(10,2) not null,
	  `vl_total_item` decimal  (10,2) generated always as ((qt_comida * vl_item)) virtual,
	  `dt_venda` date not null,
	  `cd_mesa` int not null,
      `statusC` varchar(12) not null,
      `cdg_garcom` varchar(16),
      `rua` varchar(100),
      `bairro` varchar(100),
      `numero` varchar(10),
      `cidade` varchar(80),
      `estado` varchar(3),
      `opcao` varchar(45) not null
);
CREATE TABLE `tbl_usuario` (
    `cd_usuario` int(11) auto_increment primary key,
    `nm_usuario` varchar(80),
    `ds_email` varchar(80),
    `ds_senha` varchar(15),
    `ds_status` boolean not null
);
create table gerir_estoque(
	id_dado double primary key auto_increment not null,
    id_produto double not null,
    foreign key(id_produto) references Produtos(id_produto) on delete cascade,
	produto varchar(50) not null,
    categoria varchar(15) not null,
    lote varchar(12) not null,
    vencimento date not null,
    qtd_estoque integer not null,
    valor varchar(15) not null,
    data_cadastro datetime not null,
    operacao varchar(20) not null,
    entrada double not null,
	saida double not null,
    vl_total double
);