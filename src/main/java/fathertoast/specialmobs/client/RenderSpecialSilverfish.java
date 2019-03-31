package fathertoast.specialmobs.client;

import fathertoast.specialmobs.entity.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSilverfish;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly( Side.CLIENT )
class RenderSpecialSilverfish extends RenderSilverfish
{
	RenderSpecialSilverfish( RenderManager renderManager )
	{
		super( renderManager );
		addLayer( new LayerSpecialMobEyes<>( this ) );
		// Model doesn't support size parameter
		//addLayer( new LayerSpecialMobOverlay<>( this, new ModelSilverfish( 0.25F ) ) );
	}
	
	/// Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	@Override
	protected
	ResourceLocation getEntityTexture( EntitySilverfish entity )
	{
		return ((ISpecialMob) entity).getSpecialData( ).getTexture( );
	}
	
	/// Allows the render to do any OpenGL state modifications necessary before the model is rendered.
	@Override
	protected
	void preRenderCallback( EntitySilverfish entity, float partialTick )
	{
		super.preRenderCallback( entity, partialTick );
		float scale = ((ISpecialMob) entity).getSpecialData( ).getRenderScale( );
		shadowSize = 0.3F * scale;
		GlStateManager.scale( scale, scale, scale );
	}
}
