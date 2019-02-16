/**
 * Aplicacion.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
	
	pesoGigas:{
      type:"number"
    },
	versiones:{
      type:"number"
    },
	nombres:{
      type:"string"
    },
	urlDescarga:{
      type:"string"
    },
	fechaLanzamiento:{
      type:"string"
    },
	costo:{
      type:"number"
    },
	soId:{
      model:"Sistema_operativo"
    },
	aplicacionPorUsuario:{
		collection:'AplicacionPorUsuario',
		via:'idAplicacion'
	},
	interaccionesTipoBatalla:{
		collection:'InteraccionesTipoBatalla',
		via:'idAplicacion'
	},
	interaccionesTipoRecoleccion:{
		collection:'InteraccionesTipoRecoleccion',
		via:'idAplicacion'
	},
	tipoAplicacion:{
		type:'string',
		isIn:['batalla','recoleccion','personaje']
	}
		

    //  ╔═╗╦═╗╦╔╦╗╦╔╦╗╦╦  ╦╔═╗╔═╗
    //  ╠═╝╠╦╝║║║║║ ║ ║╚╗╔╝║╣ ╚═╗
    //  ╩  ╩╚═╩╩ ╩╩ ╩ ╩ ╚╝ ╚═╝╚═╝


    //  ╔═╗╔╦╗╔╗ ╔═╗╔╦╗╔═╗
    //  ║╣ ║║║╠╩╗║╣  ║║╚═╗
    //  ╚═╝╩ ╩╚═╝╚═╝═╩╝╚═╝


    //  ╔═╗╔═╗╔═╗╔═╗╔═╗╦╔═╗╔╦╗╦╔═╗╔╗╔╔═╗
    //  ╠═╣╚═╗╚═╗║ ║║  ║╠═╣ ║ ║║ ║║║║╚═╗
    //  ╩ ╩╚═╝╚═╝╚═╝╚═╝╩╩ ╩ ╩ ╩╚═╝╝╚╝╚═╝

  },

};

