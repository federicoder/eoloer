import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { RappresentanzaPraticaUpdateComponent } from 'app/entities/rappresentanza-pratica/rappresentanza-pratica-update.component';
import { RappresentanzaPraticaService } from 'app/entities/rappresentanza-pratica/rappresentanza-pratica.service';
import { RappresentanzaPratica } from 'app/shared/model/rappresentanza-pratica.model';

describe('Component Tests', () => {
  describe('RappresentanzaPratica Management Update Component', () => {
    let comp: RappresentanzaPraticaUpdateComponent;
    let fixture: ComponentFixture<RappresentanzaPraticaUpdateComponent>;
    let service: RappresentanzaPraticaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [RappresentanzaPraticaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RappresentanzaPraticaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RappresentanzaPraticaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RappresentanzaPraticaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RappresentanzaPratica(123);
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
        const entity = new RappresentanzaPratica();
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
