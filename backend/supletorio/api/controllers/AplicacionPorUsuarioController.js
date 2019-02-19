/**
 * AplicacionPorUsuarioController
 *
 * @description :: Server-side actions for handling incoming requests.
 * @help        :: See https://sailsjs.com/docs/concepts/actions
 */

module.exports = {

    usuarios: async (req, res) => {
        const parametros = req.allParams();
        var usuarioLogeado = await
          Usuario.find({
            where: {
              idUsuario: parametros.idUsuario,
            }
          });
    
        if (usuarioLogeado) {
          return res.ok(usuarioLogeado);
        }
        else {
          return res.badRequest({ mensaje: 'Usuario Invalido' });
        }
      },

};

