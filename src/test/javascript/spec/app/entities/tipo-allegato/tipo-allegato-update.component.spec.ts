import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { TipoAllegatoUpdateComponent } from 'app/entities/tipo-allegato/tipo-allegato-update.component';
import { TipoAllegatoService } from 'app/entities/tipo-allegato/tipo-allegato.service';
import { TipoAllegato } from 'app/shared/model/tipo-allegato.model';

describe('Component Tests', () => {
  describe('TipoAllegato Management Update Component', () => {
    let comp: TipoAllegatoUpdateComponent;
    let fixture: ComponentFixture<TipoAllegatoUpdateComponent>;
    let service: TipoAllegatoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [TipoAllegatoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TipoAllegatoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TipoAllegatoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TipoAllegatoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TipoAllegato(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new TipoAllegato();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
