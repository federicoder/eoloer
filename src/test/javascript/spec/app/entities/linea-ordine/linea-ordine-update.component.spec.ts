import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { LineaOrdineUpdateComponent } from 'app/entities/linea-ordine/linea-ordine-update.component';
import { LineaOrdineService } from 'app/entities/linea-ordine/linea-ordine.service';
import { LineaOrdine } from 'app/shared/model/linea-ordine.model';

describe('Component Tests', () => {
  describe('LineaOrdine Management Update Component', () => {
    let comp: LineaOrdineUpdateComponent;
    let fixture: ComponentFixture<LineaOrdineUpdateComponent>;
    let service: LineaOrdineService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [LineaOrdineUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(LineaOrdineUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LineaOrdineUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LineaOrdineService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new LineaOrdine(123);
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
        const entity = new LineaOrdine();
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
