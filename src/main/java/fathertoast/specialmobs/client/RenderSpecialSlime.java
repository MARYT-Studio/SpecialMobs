package fathertoast.specialmobs.client;

import fathertoast.specialmobs.entity.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSlime;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly( Side.CLIENT )
class RenderSpecialSlime extends RenderSlime
{
	RenderSpecialSlime( RenderManager renderManager )
	{
		super( renderManager );
		addLayer( new LayerSpecialMobEyes<>( this ) );
		// Model doesn't support size parameter
		//addLayer( new LayerSpecialMobOverlay<>( this, new ModelSlime( 0.25F ) ) );
	}
	
	// Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	@Override
	protected
	ResourceLocation getEntityTexture( EntitySlime entity )
	{
		return ((ISpecialMob) entity).getSpecialData( ).getTexture( );
	}
	
	// Allows the render to do any OpenGL state modifications necessary before the model is rendered.
	@Override
	protected
	void preRenderCallback( EntitySlime entity, float partialTick )
	{
		super.preRenderCallback( entity, partialTick );
		float scale = ((ISpecialMob) entity).getSpecialData( ).getRenderScale( );
		shadowSize = 0.25F * (float) entity.getSlimeSize( ) * scale;
		GlStateManager.scale( scale, scale, scale );
	}
}
